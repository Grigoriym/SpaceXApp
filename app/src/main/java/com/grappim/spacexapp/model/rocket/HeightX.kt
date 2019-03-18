package com.grappim.spacexapp.model.rocket

import com.google.gson.annotations.SerializedName

data class HeightX(
  @SerializedName("feet")
  val feet: Double?,
  @SerializedName("meters")
  val meters: Int?
)