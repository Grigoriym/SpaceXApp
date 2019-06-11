package com.grappim.spacexapp.ui.cores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CoresAdapter
import com.grappim.spacexapp.ui.SharedFragment
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_get_cores.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class GetCoresFragment : SharedFragment(), KoinComponent {

//  override val kodein by kodein()

  private lateinit var coreAdapter: CoresAdapter
  private val args: GetCoresFragmentArgs by navArgs()
//  private val viewModelFactory: CoreViewModelFactory by instance()
  private val viewModelFactory:CoreViewModelFactory by inject()
  private val viewModel by viewModels<CoresViewModel> { viewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_get_cores, container, false)
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
      0 -> viewModel.loadAllCores()
      1 -> viewModel.loadPastCores()
      2 -> viewModel.loadUpcomingCores()
      else -> {
        pbGetCores.gone()
        srlGetCores.showSnackbar(getString(R.string.error_retrieving_data))
        findNavController().popBackStack()
      }
    }
  }

  private fun renderCores(cores: List<CoreModel>?) {
    coreAdapter.loadItems(cores!!)//todo
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
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = coreAdapter
    }
  }
}
