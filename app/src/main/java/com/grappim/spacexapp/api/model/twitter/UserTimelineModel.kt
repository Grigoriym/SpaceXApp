package com.grappim.spacexapp.api.model.twitter

import com.google.gson.annotations.SerializedName

data class UserTimelineModel(

    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("extended_entities")
    val extendedEntities: ExtendedEntities?,
    @SerializedName("favorite_count")
    val favoriteCount: Int?,
    @SerializedName("favorited")
    val favorited: Boolean?,
    @SerializedName("full_text")
    val fullText: String?,
    @SerializedName("id")
    val id: Long,
    @SerializedName("id_str")
    val idStr: String,
    @SerializedName("quoted_status")
    val quotedStatus: Status?,
    @SerializedName("quoted_status_id")
    val quotedStatusId: Long?,
    @SerializedName("quoted_status_id_str")
    val quotedStatusIdStr: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("text")
    val text: String?,
    @SerializedName("user")
    val user: User?

)