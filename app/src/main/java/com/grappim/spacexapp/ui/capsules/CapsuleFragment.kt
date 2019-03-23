package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.FieldConstants
import kotlinx.android.synthetic.main.fragment_capsule.*

class CapsuleFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_capsule, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    activity?.title = "Capsules"
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnGetAllCapsules.setOnClickListener {
      val args = Bundle()
      args.putInt(FieldConstants.CAPSULES_ARGS, 1)
      val f = GetCapsulesFragment()
      if (activity?.supportFragmentManager != null) {
        val ft: FragmentTransaction =
          (activity?.supportFragmentManager as FragmentManager).beginTransaction()
        ft.replace(R.id.contentFrame, f)
        f.arguments = args
        ft.addToBackStack(null)
        ft.commit()
      }
    }

    btnGetUpcomingCapsules.setOnClickListener {
      val args = Bundle()
      args.putInt(FieldConstants.CAPSULES_ARGS, 2)
      val fragment = GetCapsulesFragment()
      val fragmentManager = activity?.supportFragmentManager
      val ft = fragmentManager?.beginTransaction()
      ft?.replace(R.id.contentFrame, fragment)
      fragment.arguments = args
      ft?.addToBackStack(null)
      ft?.commit()
    }

    btnGetPastCapsules.setOnClickListener {
      val args = Bundle()
      args.putInt(FieldConstants.CAPSULES_ARGS, 3)
      val f = GetCapsulesFragment()
      if (activity?.supportFragmentManager != null) {
        val ft: FragmentTransaction =
          (activity?.supportFragmentManager as FragmentManager).beginTransaction()
        ft.replace(R.id.contentFrame, f)
        f.arguments = args
        ft.addToBackStack(null)
        ft.commit()
      }
    }
  }
}
