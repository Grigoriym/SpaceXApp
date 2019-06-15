package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_capsule.*
import timber.log.Timber

class CapsuleFragment : BaseFragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_capsule, container, false)
  }

  override fun renderFailure(failureText: String) {
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("CapsuleFragment - onViewCreated")

    btnGetAllCapsules.setOnClickListener {
      findNavController().navigate(CapsuleFragmentDirections.nextFragment(0))
    }

    btnGetUpcomingCapsules.setOnClickListener {
      findNavController().navigate(CapsuleFragmentDirections.nextFragment(1))
    }

    btnGetPastCapsules.setOnClickListener {
      findNavController().navigate(CapsuleFragmentDirections.nextFragment(2))
    }
  }
}
