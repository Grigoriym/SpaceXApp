package com.grappim.spacexapp.ui.capsules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.databinding.FragmentCapsuleBinding
import com.grappim.spacexapp.ui.base.BaseFragment

class CapsuleFragment : BaseFragment(R.layout.fragment_capsule) {

    private val viewBinding: FragmentCapsuleBinding by viewBinding(FragmentCapsuleBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnGetAllCapsules.setSafeOnClickListener {
            findNavController().navigate(CapsuleFragmentDirections.nextFragment(CapsulesArgs.ALL_CAPSULES.value))
        }

        viewBinding.btnGetUpcomingCapsules.setSafeOnClickListener {
            findNavController().navigate(
                CapsuleFragmentDirections.nextFragment(
                    CapsulesArgs.UPCOMING_CAPSULES.value
                )
            )
        }

        viewBinding.btnGetPastCapsules.setSafeOnClickListener {
            findNavController()
                .navigate(
                    CapsuleFragmentDirections.nextFragment(
                        CapsulesArgs.PAST_CAPSULES.value
                    )
                )
        }
    }
}
