package com.grappim.spacexapp.ui.ships

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.ShipsAdapter
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_get_ships.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class GetShipsFragment : BaseFragment(), KoinComponent {

  private lateinit var shipAdapter: ShipsAdapter
  private val viewModelFactory: ShipsViewModelFactory by inject()
  private val viewModel by viewModels<ShipsViewModel> { viewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater
      .inflate(R.layout.fragment_get_ships, container, false)

//  override fun onPrepareOptionsMenu(menu: Menu) {
//    menu.clear()
//  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("GetShipsFragment - onViewCreated")
//    setHasOptionsMenu(true)

    viewModel.apply {
      onObserve(allShips, ::renderShips)
      onFailure(failure, ::handleFailure)
    }

    bindAdapter()
    getData()

    srlGetShips.setOnRefreshListener {
      getData()
      srlGetShips.isRefreshing = false
    }
  }

  private fun getData() {
    pbGetShips.show()
    viewModel.loadAllShips()
  }

//  protected fun handleFailure(failure: Failure?) {
//    when (failure) {
//      is Failure.NetworkConnection -> renderFailure("Network Connection Error")
//      is Failure.ServerError -> renderFailure("Server Error")
//    }
//  }

  private fun renderShips(ships: List<ShipModel>?) {
    shipAdapter.loadItems(ships)
    pbGetShips.gone()
    rvGetShips.scheduleLayoutAnimation()
  }

  override fun renderFailure(failureText: String) {
    rvGetShips.showSnackbar(failureText)
    pbGetShips.gone()
    srlGetShips.isRefreshing = false
  }

  private fun bindAdapter() {
    shipAdapter = ShipsAdapter(onClick = {
      findNavController().navigate(GetShipsFragmentDirections.nextFragment(it))
    })
    rvGetShips.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = shipAdapter
    }
  }
}
