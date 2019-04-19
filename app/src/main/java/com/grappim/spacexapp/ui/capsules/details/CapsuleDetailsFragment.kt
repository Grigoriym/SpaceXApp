package com.grappim.spacexapp.ui.capsules.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.RvInnerMissionsAdapter
import com.grappim.spacexapp.ui.ScopedFragment
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.PARCELABLE_CAPSULE_MODEL
import com.grappim.spacexapp.util.PARCELABLE_MISSION_MODEL
import com.grappim.spacexapp.util.capsuleImageList
import kotlinx.android.synthetic.main.fragment_capsule_details.*
import timber.log.Timber

class CapsuleDetailsFragment : ScopedFragment() {

  private var args: CapsuleModel? = null
  private lateinit var mAdapter: RvInnerMissionsAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    args = arguments?.getParcelable(PARCELABLE_CAPSULE_MODEL)
    return inflater.inflate(R.layout.fragment_capsule_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    Timber.d("CapsuleDetailsFragment - onViewCreated")
    super.onViewCreated(view, savedInstanceState)
    tlbrCapsuleDetails.setNavigationIcon(com.google.android.material.R.drawable.abc_ic_ab_back_material)
    tlbrCapsuleDetails.setNavigationOnClickListener {
      activity?.onBackPressed()
    }

    bindAdapter()

    GlideApp.with(this)
      .load(capsuleImageList["default"])
      .into(ivCapsuleDetailsToolbar)

    args?.let {
      tlbrCapsuleDetails.title = it.capsuleSerial
      tvCapsuleDetailsDetails.text = it.details
      tvCapsuleDetailsLandings.text = it.landings.toString()
      tvCapsuleDetailsOriginalLaunch.text = it.originalLaunch
      tvCapsuleDetailsSerial.text = it.capsuleSerial
      tvCapsuleDetailsReuseCount.text = it.reuseCount.toString()
      tvCapsuleDetailsType.text = it.type?.capitalize()
      tvCapsuleDetailsStatus.text = it.status?.capitalize()
      mAdapter.loadItems(it.missions)
    }
  }

  private fun bindAdapter() {
    mAdapter = RvInnerMissionsAdapter {
      val args = Bundle()
      args.putParcelable(PARCELABLE_MISSION_MODEL, it)
      findNavController().navigate(R.id.nextFragment, args)
    }
    rvCapsuleDetails.apply {
      layoutManager = LinearLayoutManager(this.context)
      addItemDecoration(MarginItemDecorator())
      adapter = mAdapter
    }
  }
}
