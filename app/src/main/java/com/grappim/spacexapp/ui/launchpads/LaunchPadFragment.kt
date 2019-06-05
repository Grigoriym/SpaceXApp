package com.grappim.spacexapp.ui.launchpads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.ui.SharedFragment
import kotlinx.android.synthetic.main.fragment_launch_pad.*
import timber.log.Timber

class LaunchPadFragment : SharedFragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_launch_pad, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("LaunchPadFragment - onViewCreated")

    btnGetAllLaunchPads.setOnClickListener {
      findNavController().navigate(R.id.nextFragment)
    }
  }

}
