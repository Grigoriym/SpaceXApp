package com.grappim.spacexapp.ui.missionspayloads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_mission.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import retrofit2.Response
import timber.log.Timber

class MissionFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private val args: MissionFragmentArgs by navArgs()

  private val viewModelFactory: MissionSharedViewModelFactory by instance()

  private val viewModel: MissionSharedViewModel by lazy {
    ViewModelProviders
      .of(this, viewModelFactory)
      .get(MissionSharedViewModel::class.java)
  }

  private val observer = Observer<Response<PayloadModel>> { response ->
    pbMission.gone()
    if (response.isSuccessful) {
      response.body().let {
        tvMissionPayloadId.text = it?.payloadId
        tvMissionManufacturer.text = it?.manufacturer
        tvMissionNationality.text = it?.nationality ?: "N/A"
        ivMissionReused.setImageResource(setMyImageResource(it?.reused))

        elvMission.setAdapter(
          CustomExpandableListAdapter(
            context!!,
            elvMission,
            "Orbit Params",
            it!!,
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
      clMissionFragment.show()
    } else {
      clMissionFragment.gone()
      tvMissionPayloadId.showSnackbar(getString(R.string.error_retrieving_data))
      findNavController().popBackStack()
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_mission, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    Timber.d("MissionFragment - onViewCreated")
    super.onViewCreated(view, savedInstanceState)
    clMissionFragment.gone()
    pbMission.show()

    val missionName = args.missionArgs.name
    viewModel
      .getPayloadById(
        payloads[missionName]
          ?: missionName
      )
    viewModel.onePayload.observe(this, observer)
  }

}
