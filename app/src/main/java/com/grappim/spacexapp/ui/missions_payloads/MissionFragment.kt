package com.grappim.spacexapp.ui.missions_payloads

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.SpaceXApplication
import com.grappim.spacexapp.core.extensions.*
import com.grappim.spacexapp.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.util.payloads
import kotlinx.android.synthetic.main.fragment_mission.*
import timber.log.Timber
import javax.inject.Inject

//todo mission or payload?

class MissionFragment : BaseFragment() {

  @Inject
  lateinit var viewModel: MissionViewModel

  private val args: MissionFragmentArgs by navArgs()

  override fun onAttach(context: Context) {
    super.onAttach(context)
    getAppComponent().inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_mission, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("MissionFragment - onViewCreated")

    clMissionFragment.gone()
    pbMission.show()

    viewModel.apply {
      onObserve(onePayload, ::renderPayload)
      onFailure(failure, ::handleFailure)
    }

    getData()
  }

  private fun getData() {
    val missionName = args.missionArgs.name
    viewModel.loadPayloadById(
      payloads[missionName]
        ?: missionName
    )
  }

  private fun renderPayload(payload: PayloadModel?) {
    pbMission.gone()
    clMissionFragment.show()

    payload?.let {
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

  override fun renderFailure(failureText: String) {
    tvMissionPayloadId.showSnackbar(failureText)
    clMissionFragment.gone()
    pbMission.gone()
  }
}
