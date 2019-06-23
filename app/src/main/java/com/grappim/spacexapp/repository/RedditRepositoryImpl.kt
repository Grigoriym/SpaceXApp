package com.grappim.spacexapp.repository

import com.grappim.spacexapp.model.reddit.RedditListingResponse
import com.grappim.spacexapp.model.reddit.RedditModel
import com.grappim.spacexapp.pagination.Listing

class RedditRepositoryImpl : RedditRepository {
  override fun getPostsBySubreddit(sureddit: String): Listing<RedditModel> {
    TODO()
  }
}