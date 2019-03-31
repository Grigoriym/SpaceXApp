package com.grappim.spacexapp.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThrustVacuum(
  @SerializedName("kN")
  val kN: Int?,
  @SerializedName("lbf")
  val lbf: Int?
) : Parcelable