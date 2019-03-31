package com.grappim.spacexapp.ui.rockets.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.util.GlideApp
import kotlinx.android.synthetic.main.fragment_rocket_details.*

class RocketDetailsFragment : Fragment() {

  private var args: RocketModel? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    args = arguments?.getParcelable("model")
    return inflater.inflate(R.layout.fragment_rocket_details, container, false)
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

    GlideApp.with(this)
      .load("https://upload.wikimedia.org/wikipedia/commons/f/f6/COTS-1_Dragon_After_Return_from_Orbit_%28crop%29.jpg")
      .into(ivRocketDetails)

    args?.let {
      tvRocketDetailsBoosters.text = it.boosters.toString()
      tvRocketDetailsCompany.text = it.company
      tvRocketDetailsCostPerLaunch.text = it.costPerLaunch.toString()
      tvRocketDetailsDescription.text = it.description
      tvRocketDetailsType.text = it.rocketName
      tvRocketDetailsCountry.text = it.country
      tvRocketDetailsSuccessRate.text = it.successRatePct.toString()
      tvRocketDetailsStages.text = it.stages.toString()
      ivRocketDetailsActive.setImageResource(
        if (it.active!!) R.drawable.ic_check_circle_black_24dp
        else R.drawable.ic_cancel_black_24dp
      )
    }
  }

}
