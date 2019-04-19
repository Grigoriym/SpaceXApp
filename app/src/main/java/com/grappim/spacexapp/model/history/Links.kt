package com.grappim.spacexapp.model.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
  @SerializedName("article")
  val article: String?,
  @SerializedName("reddit")
  val reddit: String?,
  @SerializedName("wikipedia")
  val wikipedia: String?
) : Parcelable