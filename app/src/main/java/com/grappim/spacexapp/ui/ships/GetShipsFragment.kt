package com.grappim.spacexapp.ui.ships

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.api.model.ships.ShipModel
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_ships.pbGetShips
import kotlinx.android.synthetic.main.fragment_get_ships.rvGetShips
import kotlinx.android.synthetic.main.fragment_get_ships.srlGetShips
import timber.log.Timber
import javax.inject.Inject

class GetShipsFragment : BaseFragment(R.layout.fragment_get_ships) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ShipsViewModel by viewModels { viewModelFactory }

    private val shipAdapter: ShipsAdapter by lazy {
        ShipsAdapter(onClick = {
            findNavController().navigate(GetShipsFragmentDirections.nextFragment(it))
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getFragmentsComponent()
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("GetShipsFragment - onViewCreated")

        viewModel.apply {
            allShips.observe(viewLifecycleOwner, ::renderShips)
        }

        bindAdapter()

        srlGetShips.setOnRefreshListener {
            viewModel.loadAllShips()
            srlGetShips.isRefreshing = false
        }
    }

    private fun renderShips(resource: Resource<List<ShipModel>>) {
        pbGetShips.showOrGone(resource is Resource.Loading)
        when (resource) {
            is Resource.Error -> {
                showError(resource.exception)
            }
            is Resource.Success -> {
                shipAdapter.loadItems(resource.data)
                rvGetShips.scheduleLayoutAnimation()
            }
        }
    }

    private fun bindAdapter() {
        rvGetShips.apply {
            addItemDecoration(MarginItemDecorator())
            layoutAnimation = AnimationUtils
                .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
            adapter = shipAdapter
        }
    }

    private fun showError(throwable: Throwable) {
        rvGetShips.showSnackbar(requireContext().getErrorMessage(throwable))
    }
}
