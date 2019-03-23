package com.grappim.spacexapp.model.capsule

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mission(
  @SerializedName("flight")
  val flight: Int?,
  @SerializedName("name")
  val name: String?
) : Parcelable