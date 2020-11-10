package com.grappim.spacexapp.ui.cores

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.utils.ARG_CORES_ALL
import com.grappim.spacexapp.core.utils.ARG_CORES_PAST
import com.grappim.spacexapp.core.utils.ARG_CORES_UPCOMING
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_core.btnCoreAll
import kotlinx.android.synthetic.main.fragment_core.btnCorePast
import kotlinx.android.synthetic.main.fragment_core.btnCoreUpcoming

class CoreFragment : BaseFragment(R.layout.fragment_core) {

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
