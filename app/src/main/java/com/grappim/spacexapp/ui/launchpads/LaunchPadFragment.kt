package com.grappim.spacexapp.ui.launchpads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_launch_pad.*
import timber.log.Timber

class LaunchPadFragment : BaseFragment() {

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

  override fun renderFailure(failureText: String) {
  }
}
