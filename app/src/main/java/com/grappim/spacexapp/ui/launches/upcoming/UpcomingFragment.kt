package com.grappim.spacexapp.ui.launches.upcoming

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
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_upcoming.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class UpcomingFragment : BaseFragment(), KoinComponent {

  private lateinit var lAdapter: LaunchesAdapter
  private val viewModelFactory: UpcomingViewModelFactory by inject()
  private val viewModel by viewModels<UpcomingViewModel> { viewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_upcoming, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("UpcomingFragment - onViewCreated")

    viewModel.apply {
      onObserve(upcomingLaunches, ::renderLaunches)
      onFailure(failure, ::handleFailure)
    }

    bindAdapter()
    getData()

    srlUpcomingLaunches.setOnRefreshListener {
      getData()
      srlUpcomingLaunches.isRefreshing = false
    }
  }

  private fun getData() {
    pbUpcomingLaunches.show()
    viewModel.loadAllLaunches()
  }

  private fun bindAdapter() {
    lAdapter = LaunchesAdapter {

    }
    rvUpcomingLaunches.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = lAdapter
    }
  }

  override fun renderFailure(failureText: String) {
    rvUpcomingLaunches.showSnackbar(failureText)
    pbUpcomingLaunches.gone()
    srlUpcomingLaunches.isRefreshing = false
  }

  private fun renderLaunches(launches: List<LaunchModel>?) {
    launches?.let {
      lAdapter.loadItems(it)
    }
    pbUpcomingLaunches.gone()
    rvUpcomingLaunches.scheduleLayoutAnimation()
  }
}
