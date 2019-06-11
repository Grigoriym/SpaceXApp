package com.grappim.spacexapp.ui.missionspayloads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.ui.SharedFragment
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_mission.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response
import timber.log.Timber

//todo mission or payload?

class MissionFragment : SharedFragment(), KoinComponent {

//  override val kodein by kodein()

  private val args: MissionFragmentArgs by navArgs()
//  private val viewModelFactory: MissionViewModelFactory by instance()
  private val viewModelFactory:MissionViewModelFactory by inject()
  private val viewModel by viewModels<MissionViewModel> { viewModelFactory }

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
      ivMissionReused.setImageResource(setMyImageResource(it.reused))

      elvMission.setAdapter(
        CustomExpandableListAdapter(
          context,
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
