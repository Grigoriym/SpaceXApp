package com.grappim.spacexapp.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LandingLegs(
  @SerializedName("material")
  val material: String?,
  @SerializedName("number")
  val number: Int?
) : Parcelable