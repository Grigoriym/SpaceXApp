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
  ): View? {
    return inflater.inflate(R.layout.fragment_social_media, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    Timber.d("SocialMediaFragment - onViewCreated")
    super.onViewCreated(view, savedInstanceState)
    val smfpa = SocialMediaFragmentPagerAdapter(activity?.supportFragmentManager)
    vpSocialMedia.adapter = smfpa
    tlSocialMedia.setupWithViewPager(vpSocialMedia)
  }

}
