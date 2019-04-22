package com.grappim.spacexapp.ui.ships.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.grappim.spacexapp.R
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.RvInnerMissionsAdapter
import com.grappim.spacexapp.ui.ScopedFragment
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.setMyImageResource
import kotlinx.android.synthetic.main.fragment_ship_details.*

class ShipDetailsFragment : ScopedFragment() {

  private val args: ShipDetailsFragmentArgs by navArgs()
  private lateinit var mAdapter: RvInnerMissionsAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_ship_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    tlbrShipDetails.setNavigationIcon(com.google.android.material.R.drawable.abc_ic_ab_back_material)
    tlbrShipDetails.setNavigationOnClickListener {
      activity?.onBackPressed()
    }
    bindAdapter()
    args.shipModel.let {
      GlideApp.with(this)
        .load(it.image)
        .into(ivShipDetailsToolbar)
      tlbrShipDetails.title = it.shipName
      tvShipDetailsName.text = it.shipName
      tvShipDetailsType.text = it.shipType
      tvShipDetailsHomePort.text = it.homePort
      tvShipDetailsStatus.text = it.status
      tvShipDetailsLandings.text = getString(
        R.string.successful_attempted,
        it.successfulLandings ?: 0,
        it.attemptedLandings ?: 0
      )
      ivShipDetailsActive.setImageResource(setMyImageResource(it.active))
      mAdapter.loadItems(it.missions!!)

      for (item in it.roles.orEmpty()) {
        val chip = Chip(context)
        chip.text = item
        chip.isClickable = true
        chip.isCheckable = false
        cgShipDetails.addView(chip)
      }
    }
  }

  private fun bindAdapter() {
    mAdapter = RvInnerMissionsAdapter {
      findNavController().navigate(ShipDetailsFragmentDirections.nextFragment(it))
    }
    rvShipDetailsMissions.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      adapter = mAdapter
    }
  }

}
