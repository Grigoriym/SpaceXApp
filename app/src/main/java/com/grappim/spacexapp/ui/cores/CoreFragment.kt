package com.grappim.spacexapp.ui.cores

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_core.btnCoreAll
import kotlinx.android.synthetic.main.fragment_core.btnCorePast
import kotlinx.android.synthetic.main.fragment_core.btnCoreUpcoming

class CoreFragment : BaseFragment(R.layout.fragment_core) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCoreAll.setSafeOnClickListener {
            findNavController().navigate(
                CoreFragmentDirections.nextFragment(CoreArgs.ALL_CORES.value)
            )
        }

        btnCorePast.setSafeOnClickListener {
            findNavController().navigate(
                CoreFragmentDirections.nextFragment(CoreArgs.PAST_CORES.value)
            )
        }

        btnCoreUpcoming.setSafeOnClickListener {
            findNavController().navigate(
                CoreFragmentDirections.nextFragment(CoreArgs.UPCOMING_CORES.value)
            )
        }
    }
}
