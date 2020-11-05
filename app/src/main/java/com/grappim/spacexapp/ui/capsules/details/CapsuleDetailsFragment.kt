package com.grappim.spacexapp.ui.capsules.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getOffsetDateTime
import com.grappim.spacexapp.core.utils.DateTimeUtils
import com.grappim.spacexapp.core.utils.GlideApp
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.core.view.RvInnerMissionsAdapter
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.util.capsuleImageList
import kotlinx.android.synthetic.main.fragment_capsule_details.ivCapsuleDetailsToolbar
import kotlinx.android.synthetic.main.fragment_capsule_details.rvCapsuleDetails
import kotlinx.android.synthetic.main.fragment_capsule_details.tvCapsuleDetailsDetails
import kotlinx.android.synthetic.main.fragment_capsule_details.tvCapsuleDetailsLandings
import kotlinx.android.synthetic.main.fragment_capsule_details.tvCapsuleDetailsOriginalLaunch
import kotlinx.android.synthetic.main.fragment_capsule_details.tvCapsuleDetailsReuseCount
import kotlinx.android.synthetic.main.fragment_capsule_details.tvCapsuleDetailsSerial
import kotlinx.android.synthetic.main.fragment_capsule_details.tvCapsuleDetailsStatus
import kotlinx.android.synthetic.main.fragment_capsule_details.tvCapsuleDetailsType
import timber.log.Timber

class CapsuleDetailsFragment : BaseFragment(R.layout.fragment_capsule_details) {

    private val args: CapsuleDetailsFragmentArgs by navArgs()
    private val missionsAdapter: RvInnerMissionsAdapter by lazy {
        RvInnerMissionsAdapter {
            findNavController().navigate(CapsuleDetailsFragmentDirections.nextFragment(it))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("CapsuleDetailsFragment - onViewCreated")

        bindAdapter()

        GlideApp.with(this)
            .load(capsuleImageList["default"])
            .into(ivCapsuleDetailsToolbar)

        args.capsuleModel.let {
            tvCapsuleDetailsDetails.text = it.details
            tvCapsuleDetailsLandings.text = it.landings.toString()
            tvCapsuleDetailsOriginalLaunch.text = it.originalLaunch?.let { date ->
                DateTimeUtils.getDateTimeFormatter3().format(date.getOffsetDateTime())
            } ?: let {
                "Unknown"
            }
            tvCapsuleDetailsSerial.text = it.capsuleSerial
            tvCapsuleDetailsReuseCount.text = it.reuseCount.toString()
            tvCapsuleDetailsType.text = it.type?.capitalize()
            tvCapsuleDetailsStatus.text = it.status?.capitalize()
            missionsAdapter.loadItems(it.missions ?: listOf())
        }
    }

    private fun bindAdapter() {
        rvCapsuleDetails.apply {
            addItemDecoration(MarginItemDecorator())
            adapter = missionsAdapter
        }
    }
}
