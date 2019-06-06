package com.grappim.spacexapp.ui.capsules.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.RvInnerMissionsAdapter
import com.grappim.spacexapp.ui.SharedFragment
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.capsuleImageList
import kotlinx.android.synthetic.main.fragment_capsule_details.*
import timber.log.Timber

class CapsuleDetailsFragment : SharedFragment() {

  private val args: CapsuleDetailsFragmentArgs by navArgs()
  private lateinit var mAdapter: RvInnerMissionsAdapter

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_capsule_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    Timber.d("CapsuleDetailsFragment - onViewCreated")
    super.onViewCreated(view, savedInstanceState)

    bindAdapter()

    GlideApp.with(this)
        .load(capsuleImageList["default"])
        .into(ivCapsuleDetailsToolbar)

    args.capsuleModel.let {
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

  override fun renderFailure(failureText: String) {
  }

  private fun bindAdapter() {
    mAdapter = RvInnerMissionsAdapter {
      if (it != null) {
        findNavController().navigate(CapsuleDetailsFragmentDirections.nextFragment(it))
      }
    }
    rvCapsuleDetails.apply {
      layoutManager = LinearLayoutManager(this.context)
      addItemDecoration(MarginItemDecorator())
      adapter = mAdapter
    }
  }
}
