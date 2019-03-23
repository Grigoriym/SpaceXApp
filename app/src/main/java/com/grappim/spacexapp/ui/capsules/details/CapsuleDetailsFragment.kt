package com.grappim.spacexapp.ui.capsules.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CapsuleMissionsAdapter
import com.grappim.spacexapp.util.GlideApp
import kotlinx.android.synthetic.main.fragment_capsule_details.*

class CapsuleDetailsFragment : Fragment() {

  private var args: CapsuleModel? = null
  private lateinit var mAdapter: CapsuleMissionsAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    args = arguments?.getParcelable("model")
    return inflater.inflate(R.layout.fragment_capsule_details, container, false)
  }

  override fun onResume() {
    super.onResume()
    (activity as AppCompatActivity).supportActionBar?.hide()
  }

  override fun onStop() {
    super.onStop()
    (activity as AppCompatActivity).supportActionBar?.show()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    bindAdapter()

    GlideApp.with(this@CapsuleDetailsFragment)
      .load("https://upload.wikimedia.org/wikipedia/commons/f/f6/COTS-1_Dragon_After_Return_from_Orbit_%28crop%29.jpg")
      .into(ivCapsuleDetails)

    args?.let {
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
    mAdapter = CapsuleMissionsAdapter { }
    rvCapsuleDetails.apply {
      layoutManager = LinearLayoutManager(this.context)
      addItemDecoration(MarginItemDecorator())
      adapter = mAdapter
    }
  }
}
