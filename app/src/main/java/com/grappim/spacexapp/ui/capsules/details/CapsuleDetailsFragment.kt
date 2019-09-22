package com.grappim.spacexapp.ui.capsules.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.RvInnerMissionsAdapter
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.capsuleImageList
import com.grappim.spacexapp.util.getDateForCapsule
import kotlinx.android.synthetic.main.fragment_capsule_details.*
import timber.log.Timber

class CapsuleDetailsFragment : BaseFragment() {

  private val args: CapsuleDetailsFragmentArgs by navArgs()
  private lateinit var mAdapter: RvInnerMissionsAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_capsule_details, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("CapsuleDetailsFragment - onViewCreated")

    bindAdapter()

    GlideApp.with(this)
      .load(capsuleImageList["default"])
      .into(ivCapsuleDetailsToolbar)

    args.capsuleModel.let {
      tvCapsuleDetailsDetails.text = it.details
      tvCapsuleDetailsLandings.text = it.landings.toString()
      tvCapsuleDetailsOriginalLaunch.text = getDateForCapsule(it.originalLaunch ?: "")
      tvCapsuleDetailsSerial.text = it.capsuleSerial
      tvCapsuleDetailsReuseCount.text = it.reuseCount.toString()
      tvCapsuleDetailsType.text = it.type?.capitalize()
      tvCapsuleDetailsStatus.text = it.status?.capitalize()
      mAdapter.loadItems(it.missions)
    }
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
