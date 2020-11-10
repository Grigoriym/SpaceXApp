package com.grappim.spacexapp.api.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThrustSeaLevel(
  @SerializedName("kN")
  val kN: Int?,
  @SerializedName("lbf")
  val lbf: Int?
):Parcelable