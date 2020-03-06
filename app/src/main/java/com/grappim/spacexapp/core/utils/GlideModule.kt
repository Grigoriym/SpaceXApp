package com.grappim.spacexapp.core.utils

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.grappim.spacexapp.util.GlideRequest

@GlideModule
class GlideModule : AppGlideModule()

fun <T> GlideRequest<T>.roundCorners(cornerRadius: Int) =
  apply(RequestOptions().transform(RoundedCorners(cornerRadius)))