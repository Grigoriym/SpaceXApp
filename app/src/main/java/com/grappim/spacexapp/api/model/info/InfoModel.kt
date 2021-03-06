package com.grappim.spacexapp.api.model.info

import com.google.gson.annotations.SerializedName

data class InfoModel(
  @SerializedName("ceo")
  val ceo: String?,
  @SerializedName("coo")
  val coo: String?,
  @SerializedName("cto")
  val cto: String?,
  @SerializedName("cto_propulsion")
  val ctoPropulsion: String?,
  @SerializedName("employees")
  val employees: Double?,
  @SerializedName("founded")
  val founded: Double?,
  @SerializedName("founder")
  val founder: String?,
  @SerializedName("headquarters")
  val headquarters: Headquarters?,
  @SerializedName("launch_sites")
  val launchSites: Int?,
  @SerializedName("links")
  val links: Links?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("summary")
  val summary: String?,
  @SerializedName("test_sites")
  val testSites: Int?,
  @SerializedName("valuation")
  val valuation: Double?,
  @SerializedName("vehicles")
  val vehicles: Int?
) {
  companion object {
    fun empty() = InfoModel(
      "", "", "", "", 0.0,
      0.0, "", null, 0, null, "",
      "", 0, 0.0, 0
    )
  }
}