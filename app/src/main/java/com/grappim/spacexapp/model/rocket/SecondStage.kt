package com.grappim.spacexapp.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
): Parcelable