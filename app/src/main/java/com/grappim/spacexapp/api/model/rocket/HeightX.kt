package com.grappim.spacexapp.api.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeightX(
  @SerializedName("feet")
  val feet: Double?,
  @SerializedName("meters")
  val meters: Int?
) : Parcelable