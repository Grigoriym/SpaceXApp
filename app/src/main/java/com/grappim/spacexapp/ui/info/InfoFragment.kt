package com.grappim.spacexapp.ui.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grappim.spacexapp.R
import com.grappim.spacexapp.SpaceXApplication
import com.grappim.spacexapp.core.extensions.*
import com.grappim.spacexapp.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.elv.InfoHeadquartersAdapterItem
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_info.*
import timber.log.Timber
import javax.inject.Inject

class InfoFragment : BaseFragment() {

  @Inject
  lateinit var viewModel: InfoViewModel

  override fun onAttach(context: Context) {
    super.onAttach(context)
    getAppComponent().inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_info, container, false)

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
      tvInfoFounded.text = it.founded?.myFormat()
      tvInfoEmployees.text = it.employees?.myFormat()
      tvInfoVehicles.text = it.vehicles.toString()
      tvInfoLaunchSItes.text = it.launchSites.toString()
      tvInfoTestSites.text = it.testSites.toString()
      tvInfoCeo.text = it.ceo
      tvInfoCto.text = it.cto
      tvInfoCoo.text = it.coo
      tvInfoCtoPropulsion.text = it.ctoPropulsion
      tvInfoValuation.text = it.valuation?.myFormat()
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
