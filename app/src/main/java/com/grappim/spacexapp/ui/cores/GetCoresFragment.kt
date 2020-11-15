package com.grappim.spacexapp.ui.cores

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.api.model.cores.CoreModel
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.utils.ARG_CORES_ALL
import com.grappim.spacexapp.core.utils.ARG_CORES_PAST
import com.grappim.spacexapp.core.utils.ARG_CORES_UPCOMING
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_cores.pbGetCores
import kotlinx.android.synthetic.main.fragment_get_cores.rvGetCores
import kotlinx.android.synthetic.main.fragment_get_cores.srlGetCores
import timber.log.Timber
import javax.inject.Inject

class GetCoresFragment : BaseFragment(R.layout.fragment_get_cores) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CoresViewModel by viewModels { viewModelFactory }

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
        viewModel.apply {
            allCores.observe(viewLifecycleOwner, ::renderCores)
            upcomingCores.observe(viewLifecycleOwner, ::renderCores)
            pastCores.observe(viewLifecycleOwner, ::renderCores)
        }
        bindAdapter()
        getData()

        srlGetCores.setOnRefreshListener {
            getData()
            srlGetCores.isRefreshing = false
        }
    }

    private fun getData() {
        when (args.coresToGetArgs) {
            ARG_CORES_ALL -> viewModel.loadAllCores()
            ARG_CORES_PAST -> viewModel.loadPastCores()
            ARG_CORES_UPCOMING -> viewModel.loadUpcomingCores()
            else -> {
                throw IllegalStateException("wrong core args")
            }
        }
    }

    private fun renderCores(event: Resource<List<CoreModel>>) {
        pbGetCores.showOrGone(event is Resource.Loading)
        when (event) {
            is Resource.Error -> {
                showError(event.exception)
            }
            is Resource.Success -> {
                coresAdapter.loadItems(event.data)
                rvGetCores.scheduleLayoutAnimation()
            }
        }
    }

    private fun bindAdapter() {
        rvGetCores.apply {
            addItemDecoration(MarginItemDecorator())
            layoutAnimation = AnimationUtils
                .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
            adapter = coresAdapter
        }
    }

    private fun showError(throwable: Throwable) {
        rvGetCores.showSnackbar(requireContext().getErrorMessage(throwable))
    }
}
