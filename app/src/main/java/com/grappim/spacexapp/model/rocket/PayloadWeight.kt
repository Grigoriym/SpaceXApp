package com.grappim.spacexapp.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PayloadWeight(
  @SerializedName("id")
  val id: String?,
  @SerializedName("kg")
  val kg: Int?,
  @SerializedName("lb")
  val lb: Int?,
  @SerializedName("name")
  val name: String?
) : Parcelable