package com.grappim.spacexapp.network.interceptors

data class OauthKeys(
  val consumerKey: String,
  val consumerSecret: String,
  val accessToken: String? = null,
  val accessSecret: String? = null
)