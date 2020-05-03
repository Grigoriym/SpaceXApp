package com.grappim.spacexapp.ui.launchpads

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.*
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_launch_pads.*
import javax.inject.Inject

class GetLaunchPadsFragment : BaseFragment() {

  @Inject
  lateinit var viewModel: LaunchPadViewModel

  private lateinit var lAdapter: LaunchPadsAdapter

  override fun onAttach(context: Context) {
    super.onAttach(context)
    getAppComponent().inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_get_launch_pads, container, false)

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel.apply {
      onObserve(allLaunchPads, ::renderLaunchPads)
      onFailure(failure, ::handleFailure)
    }

    bindAdapter()
    getData()

    srlGetLaunchPads.setOnRefreshListener {
      getData()
      srlGetLaunchPads.isRefreshing = false
    }
  }

  private fun renderLaunchPads(rockets: List<LaunchPadModel>?) {
    lAdapter.loadItems(rockets ?: listOf())
    pbGetLaunchPads.gone()
    rvGetLaunchPads.scheduleLayoutAnimation()
  }

  override fun renderFailure(failureText: String) {
    rvGetLaunchPads.showSnackbar(failureText)
    pbGetLaunchPads.gone()
    srlGetLaunchPads.isRefreshing = false
  }

  private fun getData() {
    pbGetLaunchPads.show()
    viewModel.loadAllLaunchPads()
  }

  private fun bindAdapter() {
    lAdapter = LaunchPadsAdapter {
      findNavController().navigate(GetLaunchPadsFragmentDirections.nextFragment(it))
    }
    rvGetLaunchPads.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      adapter = lAdapter
    }
  }
}
