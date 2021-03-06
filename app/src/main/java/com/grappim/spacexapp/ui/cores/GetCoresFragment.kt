package com.grappim.spacexapp.ui.cores

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.spacexapp.R
import com.grappim.spacexapp.api.model.cores.CoreModel
import com.grappim.spacexapp.core.extensions.fragmentViewModels
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.databinding.FragmentGetCoresBinding
import com.grappim.spacexapp.ui.base.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class GetCoresFragment : BaseFragment(R.layout.fragment_get_cores) {

    @Inject
    lateinit var viewModelFactory: CoresViewModel.Factory

    private val viewBinding: FragmentGetCoresBinding by viewBinding(FragmentGetCoresBinding::bind)

    private val viewModel: CoresViewModel by fragmentViewModels {
        viewModelFactory.create(
            CoreArgs.fromStringToArgs(args.coresToGetArgs)
        )
    }

    private val coresAdapter by lazy {
        CoresAdapter {}
    }
    private val args: GetCoresFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getFragmentsComponent()
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("GetCoresFragment - onViewCreated")
        bindAdapter()
        initViewModel()

        viewBinding.srlGetCores.setOnRefreshListener {
            viewModel.loadCores()
            viewBinding.srlGetCores.isRefreshing = false
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            allCores.observe(viewLifecycleOwner, ::renderCores)
            upcomingCores.observe(viewLifecycleOwner, ::renderCores)
            pastCores.observe(viewLifecycleOwner, ::renderCores)
        }
    }

    private fun renderCores(event: Resource<List<CoreModel>>) {
        viewBinding.pbGetCores.showOrGone(event is Resource.Loading)
        when (event) {
            is Resource.Error -> {
                showError(event.exception)
            }
            is Resource.Success -> {
                coresAdapter.loadItems(event.data)
                viewBinding.rvGetCores.scheduleLayoutAnimation()
            }
        }
    }

    private fun bindAdapter() {
        viewBinding.rvGetCores.apply {
            addItemDecoration(MarginItemDecorator())
            layoutAnimation = AnimationUtils.loadLayoutAnimation(
                requireContext(),
                R.anim.layout_animation_down_to_up
            )
            adapter = coresAdapter
        }
    }

    private fun showError(throwable: Throwable) {
        viewBinding.rvGetCores.showSnackbar(requireContext().getErrorMessage(throwable))
    }
}
