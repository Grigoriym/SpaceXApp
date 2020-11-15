package com.grappim.spacexapp.core.extensions

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.startBrowser(uriString: String?) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(uriString)
    activity?.startActivity(i)
}