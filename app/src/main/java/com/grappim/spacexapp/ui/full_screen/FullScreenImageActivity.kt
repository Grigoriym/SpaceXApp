package com.grappim.spacexapp.ui.full_screen

import android.os.Bundle
import coil.api.load
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.utils.PARCELABLE_TWITTER_IMAGES
import kotlinx.android.synthetic.main.activity_full_screen_image.ivFulScreen

class FullScreenImageActivity : FullScreenBaseActivity(R.layout.activity_full_screen_image) {

    var args: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.apply {
            args = getStringExtra(PARCELABLE_TWITTER_IMAGES)
        }

        args?.apply {
            ivFulScreen.load(this)
        }
    }

}
