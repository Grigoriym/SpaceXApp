package com.grappim.spacexapp.model.rocket

import com.google.gson.annotations.SerializedName

data class Thrust(
  @SerializedName("kN")
  val kN: Int?,
  @SerializedName("lbf")
  val lbf: Int?
)