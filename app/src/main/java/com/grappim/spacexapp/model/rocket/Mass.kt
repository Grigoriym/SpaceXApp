package com.grappim.spacexapp.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mass(
  @SerializedName("kg")
  val kg: Int?,
  @SerializedName("lb")
  val lb: Int?
) : Parcelable