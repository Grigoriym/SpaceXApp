package com.grappim.spacexapp.model.twitter


import com.google.gson.annotations.SerializedName

data class VideoInfo(
  @SerializedName("aspect_ratio")
  val aspectRatio: List<Int?>?,
  @SerializedName("duration_millis")
  val durationMillis: Int?,
  @SerializedName("variants")
  val variants: List<Variant?>?
)