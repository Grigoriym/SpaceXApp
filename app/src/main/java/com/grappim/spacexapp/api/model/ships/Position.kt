package com.grappim.spacexapp.api.model.ships

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Position(
  @SerializedName("latitude")
  val latitude: Double?,
  @SerializedName("longitude")
  val longitude: Double?
) : Parcelable