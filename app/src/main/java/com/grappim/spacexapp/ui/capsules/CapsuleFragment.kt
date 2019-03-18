package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.grappim.spacexapp.R
import kotlinx.android.synthetic.main.fragment_capsule.*

class CapsuleFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_capsule, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.title = "Capsules"
    btnGetAllCapsules.setOnClickListener {
      val newFragment = GetCapsulesFragment()
      if (activity?.supportFragmentManager != null) {
        val ft: FragmentTransaction =
          (activity?.supportFragmentManager as FragmentManager).beginTransaction()
        ft.replace(R.id.contentFrame, newFragment)
        ft.addToBackStack(null)
        ft.commit()
      }

    }

    btnGetUpcomingCapsules.setOnClickListener {

    }
  }
}
