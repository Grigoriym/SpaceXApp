package com.grappim.spacexapp.ui.launches.completed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.recyclerview.LaunchesAdapter
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.ui.launches.details.LaunchDetailsActivity
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_completed_launches.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class CompletedLaunchesFragment : BaseFragment(), KoinComponent {

  private lateinit var lAdapter: LaunchesAdapter
  private val launchesViewModelFactory: CompletedLaunchesViewModelFactory by inject()
  private val viewModel by viewModels<CompletedLaunchesViewModel> { launchesViewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_completed_launches, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("CompletedLaunchesFragment - onViewCreated")

    viewModel.apply {
      onObserve(pastLaunches, ::renderLaunches)
      onFailure(failure, ::handleFailure)
    }

    bindAdapter()
    getData()

    srlCompletedLaunches.setOnRefreshListener {
      getData()
      srlCompletedLaunches.isRefreshing = false
    }
  }

  private fun getData() {
    pbCompletedLaunches.show()
    viewModel.loadPastLaunches()
  }

  private fun bindAdapter() {
    lAdapter = LaunchesAdapter {
      context?.launchActivity<LaunchDetailsActivity> {
        putExtra(PARCELABLE_LAUNCH_MODEL, it)
      }
    }
    rvCompletedLaunches.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = lAdapter
    }
  }

  override fun renderFailure(failureText: String) {
    rvCompletedLaunches.showSnackbar(failureText)
    pbCompletedLaunches.gone()
    srlCompletedLaunches.isRefreshing = false
  }

  private fun renderLaunches(launches: List<LaunchModel>?) {
    launches?.let {
      lAdapter.loadItems(it)
    }
    pbCompletedLaunches.gone()
    rvCompletedLaunches.scheduleLayoutAnimation()
  }

}
