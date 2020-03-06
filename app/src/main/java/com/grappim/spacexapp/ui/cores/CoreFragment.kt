package com.grappim.spacexapp.ui.cores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.utils.ARG_CORES_ALL
import com.grappim.spacexapp.core.utils.ARG_CORES_PAST
import com.grappim.spacexapp.core.utils.ARG_CORES_UPCOMING
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import kotlinx.android.synthetic.main.fragment_core.*

class CoreFragment : BaseFragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_core, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btnCoreAll.setSafeOnClickListener {
      findNavController().navigate(CoreFragmentDirections.nextFragment(ARG_CORES_ALL))
    }

    btnCorePast.setSafeOnClickListener {
      findNavController().navigate(CoreFragmentDirections.nextFragment(ARG_CORES_PAST))
    }

    btnCoreUpcoming.setSafeOnClickListener {
      findNavController().navigate(CoreFragmentDirections.nextFragment(ARG_CORES_UPCOMING))
    }
  }
}
