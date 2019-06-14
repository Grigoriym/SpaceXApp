package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import kotlinx.android.synthetic.main.fragment_capsule.*
import timber.log.Timber

class CapsuleFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_capsule, container, false)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    Timber.d("CapsuleFragment - onCreateOptionsMenu")
    super.onCreateOptionsMenu(menu, inflater)

  }

  override fun onPrepareOptionsMenu(menu: Menu) {
    Timber.d("CapsuleFragment - onPrepareOptionsMenu")
    super.onPrepareOptionsMenu(menu)

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("CapsuleFragment - onViewCreated")
    setHasOptionsMenu(false)

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
