package com.grappim.spacexapp.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.RocketsAdapter
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_get_rockets.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetRocketsFragment : BaseFragment(), KoinComponent {

  private val viewModelFactory: RocketsViewModelFactory by inject()
  private lateinit var rAdapter: RocketsAdapter
  private val viewModel by viewModels<RocketsViewModel> { viewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_get_rockets, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.apply {
      onObserve(allRockets, ::renderRockets)
      onFailure(failure, ::handleFailure)
    }

    bindAdapter()
    getData()

    srlGetRockets.setOnRefreshListener {
      getData()
      srlGetRockets.isRefreshing = false
    }
  }

  private fun getData() {
    pbGetRockets.show()
    viewModel.loadRockets()
  }

  private fun renderRockets(rockets: List<RocketModel>?) {
    rAdapter.loadItems(rockets ?: listOf())
    pbGetRockets.gone()
    rvGetRockets.scheduleLayoutAnimation()
  }

  override fun renderFailure(failureText: String) {
    rvGetRockets.showSnackbar(failureText)
    pbGetRockets.gone()
    srlGetRockets.isRefreshing = false
  }

  private fun bindAdapter() {
    rAdapter = RocketsAdapter(context) {
      findNavController().navigate(GetRocketsFragmentDirections.nextFragment(it))
    }
    rvGetRockets.apply {
      layoutManager = LinearLayoutManager(this.context)
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = rAdapter
    }
  }
}