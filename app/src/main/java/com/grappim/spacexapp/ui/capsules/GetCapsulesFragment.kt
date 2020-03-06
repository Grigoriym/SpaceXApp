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
import com.grappim.spacexapp.core.extensions.*
import com.grappim.spacexapp.core.utils.ARG_CAPSULES_ALL
import com.grappim.spacexapp.core.utils.ARG_CAPSULES_PAST
import com.grappim.spacexapp.core.utils.ARG_CAPSULES_UPCOMING
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CapsulesAdapter
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_capsules.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

open class GetCapsulesFragment : BaseFragment(), KoinComponent {

  private lateinit var cAdapter: CapsulesAdapter
  private val viewModelFactory: CapsuleViewModelFactory by inject()
  private val args: GetCapsulesFragmentArgs by navArgs()
  private val viewModel by viewModels<CapsulesViewModel> { viewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_get_capsules, container, false)

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
    pbGetCapsules.show()
    when (args.capsulesToGetArgs) {
      ARG_CAPSULES_ALL -> viewModel.loadAllCapsules()
      ARG_CAPSULES_UPCOMING -> viewModel.loadUpcomingCapsules()
      ARG_CAPSULES_PAST -> viewModel.loadPastCapsules()
      else -> {
        renderFailure(getString(R.string.error_retrieving_data))
      }
    }
  }

  private fun renderCapsules(capsules: List<CapsuleModel>?) {
    capsules?.let {
      cAdapter.loadItems(it)
    }
    pbGetCapsules.gone()
    rvGetCapsules.scheduleLayoutAnimation()
  }

  override fun renderFailure(failureText: String) {
    rvGetCapsules.showSnackbar(failureText)
    pbGetCapsules.gone()
    srlGetCapsules.isRefreshing = false
  }

  private fun bindAdapter() {
    cAdapter = CapsulesAdapter {
      findNavController().navigate(GetCapsulesFragmentDirections.nextFragment(it))
    }
    rvGetCapsules.apply {
      layoutManager = LinearLayoutManager(requireContext())
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
      adapter = cAdapter
    }
  }
}
