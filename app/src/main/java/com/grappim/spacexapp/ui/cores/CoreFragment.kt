package com.grappim.spacexapp.ui.cores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.CORES_ARGS
import kotlinx.android.synthetic.main.fragment_core.*

class CoreFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_core, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnCoreAll.setOnClickListener {
      val args = Bundle()
      args.putInt(CORES_ARGS, 1)
      findNavController().navigate(R.id.nextFragment, args)
    }

    btnCorePast.setOnClickListener {
      val args = Bundle()
      args.putInt(CORES_ARGS, 2)
      findNavController().navigate(R.id.nextFragment, args)
    }

    btnCoreUpcoming.setOnClickListener {
      val args = Bundle()
      args.putInt(CORES_ARGS, 3)
      findNavController().navigate(R.id.nextFragment, args)
    }
  }
}
