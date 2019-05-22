package com.grappim.spacexapp.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.elv.InfoHeadquartersAdapterItem
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import com.grappim.spacexapp.util.showSnackbar
import kotlinx.android.synthetic.main.fragment_info.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import retrofit2.Response
import timber.log.Timber

class InfoFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private val viewModelFactory: InfoViewModelFactory by instance()

  private val viewModel by viewModels<InfoViewModel> { viewModelFactory }

  private val observer = Observer<Response<InfoModel>> { response ->
    pbInfo.gone()
    if (response.isSuccessful) {
      clFragmentInfo.show()
      response.body()?.let {
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
    } else {
      clFragmentInfo.gone()
      clFragmentInfo.showSnackbar(getString(R.string.error_retrieving_data))
      findNavController().popBackStack()
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_info, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    Timber.d("InfoFragment - onActivityCreated")
    super.onActivityCreated(savedInstanceState)
    clFragmentInfo.gone()
    pbInfo.show()
    viewModel.info.observe(this, observer)
    viewModel.getInfo()
  }
}
