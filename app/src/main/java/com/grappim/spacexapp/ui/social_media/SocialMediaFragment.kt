package com.grappim.spacexapp.ui.social_media

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.grappim.spacexapp.R
import kotlinx.android.synthetic.main.fragment_social_media.tlSocialMedia
import kotlinx.android.synthetic.main.fragment_social_media.vpSocialMedia
import timber.log.Timber

class SocialMediaFragment : Fragment(R.layout.fragment_social_media) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("SocialMediaFragment - onViewCreated")
        val smfpa = SocialMediaFragmentPagerAdapter(requireActivity().supportFragmentManager)
        vpSocialMedia.adapter = smfpa
        tlSocialMedia.setupWithViewPager(vpSocialMedia)
    }

}
