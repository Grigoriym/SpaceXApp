package com.grappim.spacexapp.ui.missionspayloads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.Mission
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.ui.CustomExpandableListAdapter
import kotlinx.android.synthetic.main.fragment_mission.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class MissionFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private val viewModelFactory: MissionSharedViewModelFactory by instance()

  private val observer = Observer<PayloadModel> {
    Timber.d(it.toString())

    tvMissionPayloadId.text = it.payloadId
    tvMissionManufacturer.text = it.manufacturer
    tvMissionNationality.text = it.nationality ?: "N/A"
    ivMissionReused.setImageResource(
      if (it?.reused!!) R.drawable.ic_check_circle_black_24dp
      else R.drawable.ic_cancel_black_24dp
    )

    elvMission.setAdapter(
      CustomExpandableListAdapter(
        context!!,
        elvMission,
        "Orbit Params",
        it,
        R.layout.layout_elv_mission_item
      ) { view ->
        MissionsPayloadsListAdapterItem(
          view,
          it
        ).fillItemWithData()
      }
    )
  }

  private var args: Mission? = null

  private val viewModel: MissionSharedViewModel by lazy {
    ViewModelProviders
      .of(this, viewModelFactory)
      .get(MissionSharedViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    args = arguments?.getParcelable("model")
    return inflater.inflate(R.layout.fragment_mission, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.getPayloadById(args?.name?.replace(" ", "-"))
    viewModel.onePayload.observe(this, observer)
  }


}
