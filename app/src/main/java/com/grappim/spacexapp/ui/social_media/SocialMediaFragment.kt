package com.grappim.spacexapp.ui.social_media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.grappim.spacexapp.R
import kotlinx.android.synthetic.main.fragment_social_media.*
import timber.log.Timber

class SocialMediaFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_social_media, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("SocialMediaFragment - onViewCreated")
    val smfpa = SocialMediaFragmentPagerAdapter(requireActivity().supportFragmentManager)
    vpSocialMedia.adapter = smfpa
    tlSocialMedia.setupWithViewPager(vpSocialMedia)
  }

}
