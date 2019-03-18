package com.grappim.spacexapp.model.rocket

import com.google.gson.annotations.SerializedName

data class Diameter(
  @SerializedName("feet")
  val feet: Double?,
  @SerializedName("meters")
  val meters: Double?
)