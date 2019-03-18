package com.grappim.spacexapp.model.rocket

import com.google.gson.annotations.SerializedName

data class FirstStage(
  @SerializedName("burn_time_sec")
  val burnTimeSec: Int?,
  @SerializedName("engines")
  val engines: Int?,
  @SerializedName("fuel_amount_tons")
  val fuelAmountTons: Int?,
  @SerializedName("reusable")
  val reusable: Boolean?,
  @SerializedName("thrust_sea_level")
  val thrustSeaLevel: ThrustSeaLevel?,
  @SerializedName("thrust_vacuum")
  val thrustVacuum: ThrustVacuum?
)