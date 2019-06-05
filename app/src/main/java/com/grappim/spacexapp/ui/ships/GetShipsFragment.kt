package com.grappim.spacexapp.ui.ships

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.ShipsAdapter
import com.grappim.spacexapp.ui.SharedFragment
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import com.grappim.spacexapp.util.showSnackbar
import kotlinx.android.synthetic.main.fragment_get_ships.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import retrofit2.Response

class GetShipsFragment : SharedFragment(), KodeinAware {

  override val kodein by kodein()
  private lateinit var shipAdapter: ShipsAdapter

  private val viewModelFactory: ShipsSharedViewModelFactory by instance()

  private val viewModel by viewModels<ShipsSharedViewModel> { viewModelFactory }

  private val observer = Observer<Response<List<ShipModel>>> {
    pbGetShips.gone()
    if (it.isSuccessful) {
      it.body()?.let { items -> shipAdapter.loadItems(items) }
    } else {
      srlGetShips.showSnackbar(getString(R.string.error_retrieving_data))
    }
    rvGetShips.scheduleLayoutAnimation()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_get_ships, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.apply {
      allShips.observe(this@GetShipsFragment, observer)
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
    viewModel.getAllShips()
  }

  private fun bindAdapter() {
    shipAdapter = ShipsAdapter(context = context, onClick = {
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
