package com.grappim.spacexapp.model.launches


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchSite(
  @SerializedName("site_id")
  val siteId: String?,
  @SerializedName("site_name")
  val siteName: String?,
  @SerializedName("site_name_long")
  val siteNameLong: String?
) : Parcelable