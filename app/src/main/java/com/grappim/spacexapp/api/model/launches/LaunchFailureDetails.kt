package com.grappim.spacexapp.api.model.launches


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchFailureDetails(
  @SerializedName("altitude")
  val altitude: Int?,
  @SerializedName("reason")
  val reason: String?,
  @SerializedName("time")
  val time: Int?
) : Parcelable