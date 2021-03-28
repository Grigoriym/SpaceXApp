package com.grappim.spacexapp.ui.capsules.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getOffsetDateTime
import com.grappim.spacexapp.core.utils.DateTimeUtils
import com.grappim.spacexapp.core.utils.capsuleImageList
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.core.view.RvInnerMissionsAdapter
import com.grappim.spacexapp.databinding.FragmentCapsuleDetailsBinding
import com.grappim.spacexapp.ui.base.BaseFragment
import timber.log.Timber

class CapsuleDetailsFragment : BaseFragment(R.layout.fragment_capsule_details) {

    private val args: CapsuleDetailsFragmentArgs by navArgs()
    private val missionsAdapter: RvInnerMissionsAdapter by lazy {
        RvInnerMissionsAdapter {
            findNavController().navigate(CapsuleDetailsFragmentDirections.nextFragment(it))
        }
    }
    private val viewBinding: FragmentCapsuleDetailsBinding by viewBinding(
        FragmentCapsuleDetailsBinding::bind
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("CapsuleDetailsFragment - onViewCreated")

        bindAdapter()

        viewBinding.ivCapsuleDetailsToolbar.load(capsuleImageList["default"])

        args.capsuleModel.let {
            viewBinding.tvCapsuleDetailsDetails.text = it.details
            viewBinding.tvCapsuleDetailsLandings.text = it.landings.toString()
            viewBinding.tvCapsuleDetailsOriginalLaunch.text = it.originalLaunch?.let { date ->
                DateTimeUtils.getDateTimeFormatter3().format(date.getOffsetDateTime())
            } ?: let {
                "Unknown"
            }
            viewBinding.tvCapsuleDetailsSerial.text = it.capsuleSerial
            viewBinding.tvCapsuleDetailsReuseCount.text = it.reuseCount.toString()
            viewBinding.tvCapsuleDetailsType.text = it.type?.capitalize()
            viewBinding.tvCapsuleDetailsStatus.text = it.status?.capitalize()
            missionsAdapter.loadItems(it.missions ?: listOf())
        }
    }

    private fun bindAdapter() {
        viewBinding.rvCapsuleDetails.apply {
            addItemDecoration(MarginItemDecorator())
            adapter = missionsAdapter
        }
    }
}
