package com.grappim.spacexapp.model.rocket

import com.google.gson.annotations.SerializedName

data class ThrustSeaLevel(
  @SerializedName("kN")
  val kN: Int?,
  @SerializedName("lbf")
  val lbf: Int?
)