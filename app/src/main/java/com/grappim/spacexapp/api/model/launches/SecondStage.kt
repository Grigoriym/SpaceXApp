package com.grappim.spacexapp.api.model.launches

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SecondStage(
  @SerializedName("block")
  val block: Int?,
  @SerializedName("payloads")
  val payloads: List<Payload?>?
) : Parcelable