package com.grappim.spacexapp.ui.cores

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.SpaceXApplication
import com.grappim.spacexapp.core.extensions.*
import com.grappim.spacexapp.core.utils.ARG_CORES_ALL
import com.grappim.spacexapp.core.utils.ARG_CORES_PAST
import com.grappim.spacexapp.core.utils.ARG_CORES_UPCOMING
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CoresAdapter
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_get_cores.*
import timber.log.Timber
import javax.inject.Inject

class GetCoresFragment : BaseFragment() {

  @Inject
  lateinit var viewModel: CoresViewModel

  private lateinit var coreAdapter: CoresAdapter
  private val args: GetCoresFragmentArgs by navArgs()

  override fun onAttach(context: Context) {
    super.onAttach(context)
    getAppComponent().inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_get_cores, container, false)

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
    coreAdapter.loadItems(cores ?: listOf())
    pbGetCores.gone()
    rvGetCores.scheduleLayoutAnimation()
  }

  override fun renderFailure(failureText: String) {
    rvGetCores.showSnackbar(failureText)
    pbGetCores.gone()
    srlGetCores.isRefreshing = false
  }

  private fun bindAdapter() {
    coreAdapter = CoresAdapter {}//todo ripple effect works strange on items
    rvGetCores.apply {
      layoutManager = LinearLayoutManager(requireContext())
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
      adapter = coreAdapter
    }
  }
}
