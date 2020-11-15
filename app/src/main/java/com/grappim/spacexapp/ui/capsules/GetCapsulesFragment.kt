package com.grappim.spacexapp.ui.capsules

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.api.model.capsule.CapsuleModel
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_capsules.pbGetCapsules
import kotlinx.android.synthetic.main.fragment_get_capsules.rvGetCapsules
import kotlinx.android.synthetic.main.fragment_get_capsules.swipeGetCapsules
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
        val component = context.getFragmentsComponent()
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("GetCapsulesFragment - onViewCreated")
        initViewModel()
        bindAdapter()
        getData()
        swipeGetCapsules.setOnRefreshListener {
            getData()
            swipeGetCapsules.isRefreshing = false
        }
    }

    private fun initViewModel() {
        when (args.capsulesToGetArgs) {
            CapsulesArgs.ALL_CAPSULES.value -> viewModel.allCapsules.observe(
                viewLifecycleOwner,
                ::renderCapsules
            )
            CapsulesArgs.UPCOMING_CAPSULES.value -> viewModel.upcomingCapsules.observe(
                viewLifecycleOwner,
                ::renderCapsules
            )
            CapsulesArgs.PAST_CAPSULES.value -> viewModel.pastCapsules.observe(
                viewLifecycleOwner,
                ::renderCapsules
            )
            else -> {
                throw IllegalStateException("wrong capsule args")
            }
        }
    }

    private fun getData() {
        when (args.capsulesToGetArgs) {
            CapsulesArgs.ALL_CAPSULES.value -> viewModel.loadAllCapsules()
            CapsulesArgs.UPCOMING_CAPSULES.value -> viewModel.loadUpcomingCapsules()
            CapsulesArgs.PAST_CAPSULES.value -> viewModel.loadPastCapsules()
            else -> {
                throw IllegalStateException("wrong capsule args")
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
