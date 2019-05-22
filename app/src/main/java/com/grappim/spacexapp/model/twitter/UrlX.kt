package com.grappim.spacexapp.model.twitter

data class UrlX(
  val display_url: String,
  val expanded_url: String,
  val indices: List<Int>,
  val unwound: UnwoundX,
  val url: String
)