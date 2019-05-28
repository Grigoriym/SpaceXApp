package com.grappim.spacexapp.util

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class GlideModule : AppGlideModule()

fun <T> GlideRequest<T>.roundCorners(cornerRadius: Int) =
  apply(RequestOptions().transform(RoundedCorners(cornerRadius)))