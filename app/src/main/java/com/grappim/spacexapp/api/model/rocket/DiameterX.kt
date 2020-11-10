package com.grappim.spacexapp.api.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DiameterX(
  @SerializedName("feet")
  val feet: Int?,
  @SerializedName("meters")
  val meters: Double?
) : Parcelable