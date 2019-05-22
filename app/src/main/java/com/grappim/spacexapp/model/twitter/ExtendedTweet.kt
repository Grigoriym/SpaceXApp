package com.grappim.spacexapp.model.twitter

data class ExtendedTweet(
  val display_text_range: List<Int>,
  val entities: Entities,
  val extended_entities: ExtendedEntities,
  val full_text: String
)