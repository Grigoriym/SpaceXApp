package com.grappim.spacexapp.ui.ships

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.ShipsAdapter
import kotlinx.android.synthetic.main.fragment_get_ships.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class GetShipsFragment : Fragment(), KodeinAware {

  override val kodein by kodein()
  private lateinit var shipAdapter: ShipsAdapter
  private val observer = Observer<List<ShipModel>> {
    shipAdapter.loadItems(it)
    rvGetShips.scheduleLayoutAnimation()
  }
  private val viewModelFactory: ShipsSharedViewModelFactory by instance()

  private val viewModel: ShipsSharedViewModel by lazy {
    ViewModelProviders
      .of(this, viewModelFactory)
      .get(ShipsSharedViewModel::class.java)
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
    viewModel.getAllShips()
  }

  private fun bindAdapter() {
    shipAdapter = ShipsAdapter(context = context, onClick = {
      val args = Bundle()
      args.putParcelable("model", it)
      findNavController().navigate(R.id.nextFragment, args)
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
