package com.grappim.spacexapp.ui.launchpads

import android.content.Context
import android.os.Bundle
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
import com.grappim.spacexapp.api.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_launch_pads.pbGetLaunchPads
import kotlinx.android.synthetic.main.fragment_get_launch_pads.rvGetLaunchPads
import kotlinx.android.synthetic.main.fragment_get_launch_pads.srlGetLaunchPads
import javax.inject.Inject

class GetLaunchPadsFragment : BaseFragment(R.layout.fragment_get_launch_pads) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LaunchPadViewModel by viewModels { viewModelFactory }

    private val launchPadAdapter: LaunchPadsAdapter by lazy {
        LaunchPadsAdapter {
            findNavController().navigate(GetLaunchPadsFragmentDirections.nextFragment(it))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getFragmentsComponent()
        component.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            allLaunchPads.observe(viewLifecycleOwner, ::renderLaunchPads)
        }

        bindAdapter()

        srlGetLaunchPads.setOnRefreshListener {
            viewModel.loadAllLaunchPads()
            srlGetLaunchPads.isRefreshing = false
        }
    }

    private fun renderLaunchPads(resource: Resource<List<LaunchPadModel>>) {
        pbGetLaunchPads.showOrGone(resource is Resource.Loading)
        when (resource) {
            is Resource.Error -> {
                showError(resource.exception)
            }
            is Resource.Success -> {
                launchPadAdapter.loadItems(resource.data)
                rvGetLaunchPads.scheduleLayoutAnimation()
            }
        }
    }

    private fun bindAdapter() {
        rvGetLaunchPads.apply {
            addItemDecoration(MarginItemDecorator())
            layoutAnimation = AnimationUtils.loadLayoutAnimation(
                requireContext(),
                R.anim.layout_animation_down_to_up
            )
            adapter = launchPadAdapter
        }
    }

    private fun showError(throwable: Throwable) {
        rvGetLaunchPads.showSnackbar(requireContext().getErrorMessage(throwable))
    }
}
