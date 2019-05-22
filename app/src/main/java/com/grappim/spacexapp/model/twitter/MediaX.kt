package com.grappim.spacexapp.model.twitter

data class MediaX(
  val display_url: String,
  val expanded_url: String,
  val id: Long,
  val id_str: String,
  val indices: List<Int>,
  val media_url: String,
  val media_url_https: String,
  val sizes: SizesX,
  val type: String,
  val url: String,
  val video_info: VideoInfo
)