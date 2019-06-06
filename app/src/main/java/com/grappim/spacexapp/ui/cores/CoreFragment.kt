package com.grappim.spacexapp.ui.cores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.ui.SharedFragment
import kotlinx.android.synthetic.main.fragment_core.*

class CoreFragment : SharedFragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_core, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnCoreAll.setOnClickListener {
      findNavController().navigate(CoreFragmentDirections.nextFragment(0))
    }

    btnCorePast.setOnClickListener {
      findNavController().navigate(CoreFragmentDirections.nextFragment(1))
    }

    btnCoreUpcoming.setOnClickListener {
      findNavController().navigate(CoreFragmentDirections.nextFragment(2))
    }
  }
}
