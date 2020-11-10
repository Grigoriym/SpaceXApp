package com.grappim.spacexapp.api.model.launchpads

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
  @SerializedName("latitude")
  val latitude: Double?,
  @SerializedName("longitude")
  val longitude: Double?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("region")
  val region: String?
) : Parcelable