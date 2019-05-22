package com.grappim.spacexapp.model.twitter

data class ExtendedTweetX(
  val display_text_range: List<Int>,
  val entities: EntitiesX,
  val extended_entities: ExtendedEntities,
  val full_text: String
)