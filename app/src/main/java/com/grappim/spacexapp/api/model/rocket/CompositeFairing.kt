package com.grappim.spacexapp.api.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompositeFairing(
  @SerializedName("diameter")
  val diameter: Diameter?,
  @SerializedName("height")
  val height: Height?
) : Parcelable