package com.grappim.spacexapp.ui.ships.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.google.android.material.chip.Chip
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.setMyImageResource
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.core.view.RvInnerMissionsAdapter
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_ship_details.cgShipDetails
import kotlinx.android.synthetic.main.fragment_ship_details.ivShipDetailsActive
import kotlinx.android.synthetic.main.fragment_ship_details.ivShipDetailsToolbar
import kotlinx.android.synthetic.main.fragment_ship_details.rvShipDetailsMissions
import kotlinx.android.synthetic.main.fragment_ship_details.tvShipDetailsHomePort
import kotlinx.android.synthetic.main.fragment_ship_details.tvShipDetailsLandings
import kotlinx.android.synthetic.main.fragment_ship_details.tvShipDetailsName
import kotlinx.android.synthetic.main.fragment_ship_details.tvShipDetailsStatus
import kotlinx.android.synthetic.main.fragment_ship_details.tvShipDetailsType
import timber.log.Timber

class ShipDetailsFragment : BaseFragment(R.layout.fragment_ship_details) {

    private val args: ShipDetailsFragmentArgs by navArgs()
    private lateinit var mAdapter: RvInnerMissionsAdapter

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu_wiki, menu)
        menu[0].title = "URL"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.wiki -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(args.shipModel.url)
                activity?.startActivity(i)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("ShipDetailsFragment - onViewCreated")

        bindAdapter()
        args.shipModel.let {
            ivShipDetailsToolbar.load(it.image)
            (activity as? AppCompatActivity)?.supportActionBar?.title = it.shipName
            tvShipDetailsName.text = it.shipName
            tvShipDetailsType.text = it.shipType
            tvShipDetailsHomePort.text = it.homePort
            tvShipDetailsStatus.text = it.status
            tvShipDetailsLandings.text = getString(
                R.string.successful_attempted,
                it.successfulLandings ?: 0,
                it.attemptedLandings ?: 0
            )
            ivShipDetailsActive.setImageResource(
                setMyImageResource(
                    it.active
                )
            )
            mAdapter.loadItems(it.missions ?: listOf())

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
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(MarginItemDecorator())
            adapter = mAdapter
        }
    }
}
