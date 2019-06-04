package com.grappim.spacexapp.ui.social_media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.grappim.spacexapp.R
import kotlinx.android.synthetic.main.fragment_social_media.*

class SocialMediaFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_social_media, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    TabLayoutMediator(tlSocialMedia, vpSocialMedia) { tab, position ->
      when (position) {
        0 -> tab.text = "Twitter"
        1 -> tab.text = "Reddit"
      }
    }.attach()
  }

}
