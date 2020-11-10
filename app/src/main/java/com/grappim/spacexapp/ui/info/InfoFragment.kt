package com.grappim.spacexapp.ui.info

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.myFormat
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.view.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.core.view.elv.InfoHeadquartersAdapterItem
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_info.clFragmentInfo
import kotlinx.android.synthetic.main.fragment_info.elvInfo
import kotlinx.android.synthetic.main.fragment_info.pbInfo
import kotlinx.android.synthetic.main.fragment_info.tvInfoCeo
import kotlinx.android.synthetic.main.fragment_info.tvInfoCoo
import kotlinx.android.synthetic.main.fragment_info.tvInfoCto
import kotlinx.android.synthetic.main.fragment_info.tvInfoCtoPropulsion
import kotlinx.android.synthetic.main.fragment_info.tvInfoEmployees
import kotlinx.android.synthetic.main.fragment_info.tvInfoFounded
import kotlinx.android.synthetic.main.fragment_info.tvInfoFounder
import kotlinx.android.synthetic.main.fragment_info.tvInfoLaunchSItes
import kotlinx.android.synthetic.main.fragment_info.tvInfoName
import kotlinx.android.synthetic.main.fragment_info.tvInfoSummary
import kotlinx.android.synthetic.main.fragment_info.tvInfoTestSites
import kotlinx.android.synthetic.main.fragment_info.tvInfoValuation
import kotlinx.android.synthetic.main.fragment_info.tvInfoVehicles
import timber.log.Timber
import javax.inject.Inject

class InfoFragment : BaseFragment(R.layout.fragment_info) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: InfoViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getFragmentsComponent()
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("InfoFragment - onViewCreated")

        viewModel.apply {
            info.observe(viewLifecycleOwner, ::renderInfo)
        }

        clFragmentInfo.gone()

        viewModel.loadInfo()
    }

    private fun renderInfo(resource: Resource<InfoModel>) {
        pbInfo.showOrGone(resource is Resource.Loading)
        when (resource) {
            is Resource.Error -> {
                showError(resource.exception)
            }
            is Resource.Success -> {
                clFragmentInfo.show()
                resource.data.let {
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
        }
    }

    override fun renderFailure(failureText: String) {
        clFragmentInfo.showSnackbar(failureText)
        pbInfo.gone()
    }

    private fun showError(throwable: Throwable) {
        clFragmentInfo.showSnackbar(requireContext().getErrorMessage(throwable))
    }
}
