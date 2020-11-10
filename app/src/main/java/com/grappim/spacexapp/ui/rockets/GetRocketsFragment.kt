package com.grappim.spacexapp.ui.rockets

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.api.model.rocket.RocketModel
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_rockets.pbGetRockets
import kotlinx.android.synthetic.main.fragment_get_rockets.rvGetRockets
import kotlinx.android.synthetic.main.fragment_get_rockets.srlGetRockets
import javax.inject.Inject

class GetRocketsFragment : BaseFragment(R.layout.fragment_get_rockets) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RocketsViewModel by viewModels { viewModelFactory }

    private val rocketsAdapter: RocketsAdapter by lazy {
        RocketsAdapter {
            findNavController().navigate(GetRocketsFragmentDirections.nextFragment(it))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getFragmentsComponent()
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            allRockets.observe(viewLifecycleOwner, ::renderRockets)
        }

        bindAdapter()
        getData()

        srlGetRockets.setOnRefreshListener {
            getData()
            srlGetRockets.isRefreshing = false
        }
    }

    private fun getData() {
        viewModel.loadRockets()
    }

    private fun renderRockets(resource: Resource<List<RocketModel>>) {
        pbGetRockets.showOrGone(resource is Resource.Loading)
        when (resource) {
            is Resource.Error -> {
                showError(resource.exception)
            }
            is Resource.Success -> {
                rocketsAdapter.loadItems(resource.data)
                rvGetRockets.scheduleLayoutAnimation()
            }
        }
    }

    override fun renderFailure(failureText: String) {
        rvGetRockets.showSnackbar(failureText)
        pbGetRockets.gone()
        srlGetRockets.isRefreshing = false
    }

    private fun bindAdapter() {
        rvGetRockets.apply {
            addItemDecoration(MarginItemDecorator())
            layoutAnimation = AnimationUtils
                .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
            adapter = rocketsAdapter
        }
    }

    private fun showError(throwable: Throwable) {
        rvGetRockets.showSnackbar(requireContext().getErrorMessage(throwable))
    }
}