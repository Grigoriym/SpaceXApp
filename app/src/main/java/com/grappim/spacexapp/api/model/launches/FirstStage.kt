package com.grappim.spacexapp.api.model.launches

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FirstStage(
  @SerializedName("cores")
  val cores: List<Core?>?
) : Parcelable