package com.grappim.spacexapp.api.model.rocket

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Payloads(
  @SerializedName("composite_fairing")
  val compositeFairing: CompositeFairing?,
  @SerializedName("option_1")
  val option1: String?,
  @SerializedName("option_2")
  val option2: String?
) : Parcelable