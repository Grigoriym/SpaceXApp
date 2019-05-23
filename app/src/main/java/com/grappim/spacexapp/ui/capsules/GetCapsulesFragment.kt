package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CapsulesAdapter
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import com.grappim.spacexapp.util.showSnackbar
import kotlinx.android.synthetic.main.fragment_get_capsules.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import retrofit2.Response
import timber.log.Timber

class GetCapsulesFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private lateinit var cAdapter: CapsulesAdapter

  private val viewModelFactory: CapsuleSharedViewModelFactory by instance()

  private val args: GetCapsulesFragmentArgs by navArgs()

  private val viewModel by viewModels<CapsulesViewModel> { viewModelFactory }

  private val observerWithResponse = Observer<Response<List<CapsuleModel>>> {
    Timber.d("GetCapsulesFragment - observer")
    pbGetCapsules.gone()
    if (it.isSuccessful) {
      it.body()?.let { items -> cAdapter.loadItems(items) }
    } else {
      srlGetCapsules.showSnackbar(getString(R.string.error_retrieving_data))
      findNavController().popBackStack()
    }
    rvGetCapsules.scheduleLayoutAnimation()
  }

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
      allCapsules.observe(this@GetCapsulesFragment, observerWithResponse)
      upcomingCapsules.observe(this@GetCapsulesFragment, observerWithResponse)
      pastCapsules.observe(this@GetCapsulesFragment, observerWithResponse)
    }

    bindAdapter()
    getData()

    srlGetCapsules.setOnRefreshListener {
      getData()
      srlGetCapsules.isRefreshing = false
    }
  }

  private fun getData() {
    Timber.d("GetCapsulesFragment - getData")
    pbGetCapsules.show()
    when (args.capsulesToGetArgs) {
      0 -> viewModel.getAllCapsules()
      1 -> viewModel.getUpcomingCapsules()
      2 -> viewModel.getPastCapsules()
      else -> {
        srlGetCapsules.showSnackbar(getString(R.string.error_retrieving_data))
        findNavController().popBackStack()
      }
    }
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
