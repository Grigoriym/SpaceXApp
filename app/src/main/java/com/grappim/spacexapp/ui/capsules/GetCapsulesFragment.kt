package com.grappim.spacexapp.ui.capsules

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getCapsuleComponent
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.utils.ARG_CAPSULES_ALL
import com.grappim.spacexapp.core.utils.ARG_CAPSULES_PAST
import com.grappim.spacexapp.core.utils.ARG_CAPSULES_UPCOMING
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_capsules.pbGetCapsules
import kotlinx.android.synthetic.main.fragment_get_capsules.rvGetCapsules
import kotlinx.android.synthetic.main.fragment_get_capsules.srlGetCapsules
import timber.log.Timber
import javax.inject.Inject

class GetCapsulesFragment : BaseFragment(R.layout.fragment_get_capsules) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CapsulesViewModel by viewModels { viewModelFactory }

    private val capsulesAdapter by lazy {
        CapsulesAdapter {
            findNavController().navigate(GetCapsulesFragmentDirections.nextFragment(it))
        }
    }
    private val args: GetCapsulesFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        val component = context.getCapsuleComponent()
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("GetCapsulesFragment - onViewCreated")

        viewModel.apply {
            allCapsules.observe(viewLifecycleOwner, ::renderCapsules)
            upcomingCapsules.observe(viewLifecycleOwner, ::renderCapsules)
            pastCapsules.observe(viewLifecycleOwner, ::renderCapsules)
        }

        bindAdapter()
        getData()

        srlGetCapsules.setOnRefreshListener {
            getData()
            srlGetCapsules.isRefreshing = false
        }
    }

    private fun getData() {
        when (args.capsulesToGetArgs) {
            ARG_CAPSULES_ALL -> viewModel.loadAllCapsules()
            ARG_CAPSULES_UPCOMING -> viewModel.loadUpcomingCapsules()
            ARG_CAPSULES_PAST -> viewModel.loadPastCapsules()
            else -> {
                renderFailure(getString(R.string.error_retrieving_data))
            }
        }
    }

    private fun renderCapsules(event: Resource<List<CapsuleModel>>) {
        pbGetCapsules.showOrGone(event is Resource.Loading)
        when (event) {
            is Resource.Error -> {
                showError(event.exception)
            }
            is Resource.Success -> {
                capsulesAdapter.loadItems(event.data)
            }
        }
        rvGetCapsules.scheduleLayoutAnimation()
    }

    override fun renderFailure(failureText: String) {
        rvGetCapsules.showSnackbar(failureText)
        pbGetCapsules.gone()
        srlGetCapsules.isRefreshing = false
    }

    private fun showError(throwable: Throwable) {
        rvGetCapsules.showSnackbar(requireContext().getErrorMessage(throwable))
    }

    private fun bindAdapter() {
        rvGetCapsules.apply {
            addItemDecoration(MarginItemDecorator())
            layoutAnimation = AnimationUtils.loadLayoutAnimation(
                requireContext(),
                R.anim.layout_animation_down_to_up
            )
            adapter = capsulesAdapter
        }
    }
}
