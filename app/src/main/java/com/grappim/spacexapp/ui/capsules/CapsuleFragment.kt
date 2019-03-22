package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.grappim.spacexapp.R
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptorImpl
import com.grappim.spacexapp.ui.ScopedFragment
import kotlinx.android.synthetic.main.fragment_capsule.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
