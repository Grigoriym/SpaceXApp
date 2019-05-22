package com.grappim.spacexapp.model.twitter

data class Entities(
  val hashtags: List<Any>,
  val media: List<Media>,
  val symbols: List<Any>,
  val urls: List<Url>,
  val user_mentions: List<Any>
)