package com.grappim.spacexapp.model.rocket

import com.google.gson.annotations.SerializedName

data class DiameterX(
  @SerializedName("feet")
  val feet: Int?,
  @SerializedName("meters")
  val meters: Double?
)