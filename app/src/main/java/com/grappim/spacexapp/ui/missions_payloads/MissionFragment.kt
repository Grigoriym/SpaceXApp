package com.grappim.spacexapp.ui.missions_payloads

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.setMyImageResource
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.view.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.api.model.payloads.PayloadModel
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.core.utils.payloads
import kotlinx.android.synthetic.main.fragment_mission.clMissionFragment
import kotlinx.android.synthetic.main.fragment_mission.elvMission
import kotlinx.android.synthetic.main.fragment_mission.ivMissionReused
import kotlinx.android.synthetic.main.fragment_mission.pbMission
import kotlinx.android.synthetic.main.fragment_mission.tvMissionManufacturer
import kotlinx.android.synthetic.main.fragment_mission.tvMissionNationality
import kotlinx.android.synthetic.main.fragment_mission.tvMissionPayloadId
import timber.log.Timber
import javax.inject.Inject

//todo mission or payload?

class MissionFragment : BaseFragment(R.layout.fragment_mission) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MissionViewModel by viewModels { viewModelFactory }

    private val args: MissionFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getFragmentsComponent()
        component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("MissionFragment - onViewCreated")

        clMissionFragment.gone()
        pbMission.show()

        viewModel.apply {
            onePayload.observe(viewLifecycleOwner, ::renderPayload)
        }

        getData()
    }

    private fun getData() {
        val missionName = args.missionArgs.name
        viewModel.loadPayloadById(
            payloads[missionName]
                ?: missionName ?: ""
        )
    }

    private fun renderPayload(resource: Resource<PayloadModel>) {
        pbMission.showOrGone(resource is Resource.Loading)
        when (resource) {
            is Resource.Error -> {
                showError(resource.exception)
            }
            is Resource.Success -> {
                clMissionFragment.show()
                resource.data.let {
                    tvMissionPayloadId.text = it.payloadId
                    tvMissionManufacturer.text = it.manufacturer
                    tvMissionNationality.text = it.nationality ?: "N/A"
                    ivMissionReused.setImageResource(
                        setMyImageResource(
                            it.reused
                        )
                    )

                    elvMission.setAdapter(
                        CustomExpandableListAdapter(
                            requireContext(),
                            elvMission,
                            "Orbit Params",
                            it,
                            R.layout.layout_elv_mission_item,
                            listAdapterItemInit = { view ->
                                MissionsPayloadsListAdapterItem(
                                    view,
                                    it
                                ).fillItemWithData()
                            }
                        )
                    )
                }
            }
        }

    }

    private fun showError(throwable: Throwable) {
        tvMissionPayloadId.showSnackbar(requireContext().getErrorMessage(throwable))
    }
}
