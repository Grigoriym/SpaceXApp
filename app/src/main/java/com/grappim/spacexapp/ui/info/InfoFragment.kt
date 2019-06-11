package com.grappim.spacexapp.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.elv.InfoHeadquartersAdapterItem
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.ui.SharedFragment
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_info.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class InfoFragment : SharedFragment(), KoinComponent {

//  override val kodein by kodein()
//  private val viewModelFactory: InfoViewModelFactory by instance()
  private val viewModelFactory:InfoViewModelFactory by inject()
  private val viewModel by viewModels<InfoViewModel> { viewModelFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_info, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("InfoFragment - onViewCreated")

    viewModel.apply {
      onObserve(info, ::renderInfo)
      onFailure(failure, ::handleFailure)
    }

    clFragmentInfo.gone()
    pbInfo.show()

    viewModel.loadInfo()
  }

  private fun renderInfo(item: InfoModel?) {
    pbInfo.gone()
    clFragmentInfo.show()

    item?.let {
      tvInfoName.text = it.name
      tvInfoFounder.text = it.founder
      tvInfoFounded.text = it.founded.toString()
      tvInfoEmployees.text = it.employees.toString()
      tvInfoVehicles.text = it.vehicles.toString()
      tvInfoLaunchSItes.text = it.launchSites.toString()
      tvInfoTestSites.text = it.testSites.toString()
      tvInfoCeo.text = it.ceo
      tvInfoCto.text = it.cto
      tvInfoCoo.text = it.coo
      tvInfoCtoPropulsion.text = it.ctoPropulsion
      tvInfoValuation.text = it.valuation.toString()
      tvInfoSummary.text = it.summary

      elvInfo.setAdapter(
        CustomExpandableListAdapter(
          context,
          elvInfo,
          "Headquarters",
          it,
          R.layout.layout_elv_info_headquarters,
          listAdapterItemInit = { view ->
            InfoHeadquartersAdapterItem(
              view,
              it
            ).fillItemsWithData()
          }
        )
      )
    }
  }

  override fun renderFailure(failureText: String) {
    clFragmentInfo.showSnackbar(failureText)
    pbInfo.gone()
  }
}
