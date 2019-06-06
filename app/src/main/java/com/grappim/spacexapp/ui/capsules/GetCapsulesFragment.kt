package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CapsulesAdapter
import com.grappim.spacexapp.ui.SharedFragment
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_get_capsules.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class GetCapsulesFragment : SharedFragment(), KodeinAware {

  override val kodein by kodein()
  private lateinit var cAdapter: CapsulesAdapter
  private val viewModelFactory: CapsuleViewModelFactory by instance()
  private val args: GetCapsulesFragmentArgs by navArgs()
  private val viewModel by viewModels<CapsulesViewModel> { viewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_get_capsules, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("GetCapsulesFragment - onViewCreated")

    viewModel.apply {
      onObserve(allCapsules, ::renderCapsules)
      onObserve(upcomingCapsules, ::renderCapsules)
      onObserve(pastCapsules, ::renderCapsules)
      onFailure(failure, ::handleFailure)
    }

    bindAdapter()
    getData()

    srlGetCapsules.setOnRefreshListener {
      getData()
      srlGetCapsules.isRefreshing = false
    }
  }

  private fun getData() {
    Timber.d("GetCapsulesFragment - getData - ${args.capsulesToGetArgs}")
    pbGetCapsules.show()
    when (args.capsulesToGetArgs) {
      0 -> viewModel.loadAllCapsules()
      1 -> viewModel.loadUpcomingCapsules()
      2 -> viewModel.loadPastCapsules()
      else -> {
        pbGetCapsules.gone()
        srlGetCapsules.showSnackbar(getString(R.string.error_retrieving_data))
        findNavController().popBackStack()
      }
    }
  }

  private fun renderCapsules(capsules: List<CapsuleModel>?) {
    cAdapter.loadItems(capsules!!)//todo
    pbGetCapsules.gone()
    rvGetCapsules.scheduleLayoutAnimation()
  }

  private fun handleFailure(failure: Failure?) {
    when (failure) {
      is Failure.NetworkConnection -> renderFailure("SpacexNetwork Connection Error")
      is Failure.ServerError -> renderFailure("Server Error")
    }
  }

  private fun renderFailure(failureText: String) {
    rvGetCapsules.showSnackbar(failureText)
    pbGetCapsules.gone()
    srlGetCapsules.isRefreshing = false
  }

  private fun bindAdapter() {
    cAdapter = CapsulesAdapter {
      findNavController().navigate(GetCapsulesFragmentDirections.nextFragment(it))
    }
    rvGetCapsules.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = cAdapter
    }
  }
}
