package com.grappim.spacexapp.api.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Diameter(
  @SerializedName("feet")
  val feet: Double?,
  @SerializedName("meters")
  val meters: Double?
) : Parcelable