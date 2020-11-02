package com.grappim.spacexapp.ui.cores

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getCoresComponent
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.onFailure
import com.grappim.spacexapp.core.extensions.onObserve
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.utils.ARG_CORES_ALL
import com.grappim.spacexapp.core.utils.ARG_CORES_PAST
import com.grappim.spacexapp.core.utils.ARG_CORES_UPCOMING
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.model.cores.CoreModel
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
        CoresAdapter {

        }
    }
    private val args: GetCoresFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getCoresComponent()
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("GetCoresFragment - onViewCreated")

        viewModel.apply {
            onObserve(allCores, ::renderCores)
            onObserve(upcomingCores, ::renderCores)
            onObserve(pastCores, ::renderCores)
            onFailure(failure, ::handleFailure)
        }

        bindAdapter()
        getData()

        srlGetCores.setOnRefreshListener {
            getData()
            srlGetCores.isRefreshing = false
        }
    }

    private fun getData() {
        pbGetCores.show()
        when (args.coresToGetArgs) {
            ARG_CORES_ALL -> viewModel.loadAllCores()
            ARG_CORES_PAST -> viewModel.loadPastCores()
            ARG_CORES_UPCOMING -> viewModel.loadUpcomingCores()
            else -> {
                renderFailure(getString(R.string.error_retrieving_data))
            }
        }
    }

    private fun renderCores(cores: List<CoreModel>?) {
        coresAdapter.loadItems(cores ?: listOf())
        pbGetCores.gone()
        rvGetCores.scheduleLayoutAnimation()
    }

    override fun renderFailure(failureText: String) {
        rvGetCores.showSnackbar(failureText)
        pbGetCores.gone()
        srlGetCores.isRefreshing = false
    }

    //todo ripple effect works strange on items
    private fun bindAdapter() {
        rvGetCores.apply {
            addItemDecoration(MarginItemDecorator())
            layoutAnimation = AnimationUtils
                .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
            adapter = coresAdapter
        }
    }
}
