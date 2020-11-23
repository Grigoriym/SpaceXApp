package com.grappim.spacexapp.ui.cores

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.databinding.FragmentCoreBinding
import com.grappim.spacexapp.ui.base.BaseFragment

class CoreFragment : BaseFragment(R.layout.fragment_core) {

    private val viewBinding: FragmentCoreBinding by viewBinding(FragmentCoreBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnCoreAll.setSafeOnClickListener {
            findNavController().navigate(
                CoreFragmentDirections.nextFragment(CoreArgs.ALL_CORES.value)
            )
        }

        viewBinding.btnCorePast.setSafeOnClickListener {
            findNavController().navigate(
                CoreFragmentDirections.nextFragment(CoreArgs.PAST_CORES.value)
            )
        }

        viewBinding.btnCoreUpcoming.setSafeOnClickListener {
            findNavController().navigate(
                CoreFragmentDirections.nextFragment(CoreArgs.UPCOMING_CORES.value)
            )
        }
    }
}
