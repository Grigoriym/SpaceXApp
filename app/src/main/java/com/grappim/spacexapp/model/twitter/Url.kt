package com.grappim.spacexapp.model.twitter

data class Url(
  val display_url: String,
  val expanded_url: String,
  val indices: List<Int>,
  val unwound: Unwound,
  val url: String
)