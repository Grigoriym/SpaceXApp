package com.grappim.spacexapp.model.rocket

import com.google.gson.annotations.SerializedName

data class SecondStage(
  @SerializedName("burn_time_sec")
  val burnTimeSec: Int?,
  @SerializedName("engines")
  val engines: Int?,
  @SerializedName("fuel_amount_tons")
  val fuelAmountTons: Double?,
  @SerializedName("payloads")
  val payloads: Payloads?,
  @SerializedName("thrust")
  val thrust: Thrust?
)