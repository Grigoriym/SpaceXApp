package com.grappim.spacexapp.api.model.twitter


import com.google.gson.annotations.SerializedName

data class SizesX(
  @SerializedName("large")
  val large: LargeX?,
  @SerializedName("medium")
  val medium: MediumX?,
  @SerializedName("small")
  val small: SmallX?,
  @SerializedName("thumb")
  val thumb: Thumb?
)