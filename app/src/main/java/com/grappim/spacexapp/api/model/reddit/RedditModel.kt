package com.grappim.spacexapp.api.model.reddit

import com.google.gson.annotations.SerializedName

data class RedditModel(
    @SerializedName("archived")
    val archived: Boolean?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("author_flair_type")
    val authorFlairType: String?,
    @SerializedName("author_fullname")
    val authorFullname: String?,
    @SerializedName("author_patreon_flair")
    val authorPatreonFlair: Boolean?,
    @SerializedName("can_gild")
    val canGild: Boolean?,
    @SerializedName("can_mod_post")
    val canModPost: Boolean?,
    @SerializedName("clicked")
    val clicked: Boolean?,
    @SerializedName("contest_mode")
    val contestMode: Boolean?,
    @SerializedName("created")
    val created: Int?,
    @SerializedName("created_utc")
    val createdUtc: Long,
    @SerializedName("domain")
    val domain: String?,
    @SerializedName("downs")
    val downs: Int?,
    @SerializedName("gilded")
    val gilded: Int?,
    @SerializedName("gildings")
    val gildings: Gildings?,
    @SerializedName("hidden")
    val hidden: Boolean?,
    @SerializedName("hide_score")
    val hideScore: Boolean?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("is_crosspostable")
    val isCrosspostable: Boolean?,
    @SerializedName("is_meta")
    val isMeta: Boolean?,
    @SerializedName("is_original_content")
    val isOriginalContent: Boolean?,
    @SerializedName("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean?,
    @SerializedName("is_robot_indexable")
    val isRobotIndexable: Boolean?,
    @SerializedName("is_self")
    val isSelf: Boolean?,
    @SerializedName("is_video")
    val isVideo: Boolean?,
    @SerializedName("link_flair_background_color")
    val linkFlairBackgroundColor: String?,
    @SerializedName("link_flair_css_class")
    val linkFlairCssClass: String?,
    @SerializedName("link_flair_richtext")
    val linkFlairRichtext: List<LinkFlairRichtext?>?,
    @SerializedName("link_flair_template_id")
    val linkFlairTemplateId: String?,
    @SerializedName("link_flair_text")
    val linkFlairText: String?,
    @SerializedName("link_flair_text_color")
    val linkFlairTextColor: String?,
    @SerializedName("link_flair_type")
    val linkFlairType: String?,
    @SerializedName("locked")
    val locked: Boolean?,
    @SerializedName("media_embed")
    val mediaEmbed: MediaEmbed?,
    @SerializedName("media_only")
    val mediaOnly: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("no_follow")
    val noFollow: Boolean?,
    @SerializedName("num_comments")
    val numComments: Int?,
    @SerializedName("num_crossposts")
    val numCrossposts: Int?,
    @SerializedName("over_18")
    val over18: Boolean?,
    @SerializedName("parent_whitelist_status")
    val parentWhitelistStatus: String?,
    @SerializedName("permalink")
    val permalink: String?,
    @SerializedName("pinned")
    val pinned: Boolean?,
    @SerializedName("post_hint")
    val postHint: String?,
    @SerializedName("preview")
    val preview: Preview?,
    @SerializedName("pwls")
    val pwls: Int?,
    @SerializedName("quarantine")
    val quarantine: Boolean?,
    @SerializedName("saved")
    val saved: Boolean?,
    @SerializedName("score")
    val score: Int?,
    @SerializedName("secure_media")
    val secureMedia: Any?,
    @SerializedName("secure_media_embed")
    val secureMediaEmbed: SecureMediaEmbed?,
    @SerializedName("selftext")
    val selftext: String?,
    @SerializedName("send_replies")
    val sendReplies: Boolean?,
    @SerializedName("spoiler")
    val spoiler: Boolean?,
    @SerializedName("stickied")
    val stickied: Boolean?,
    @SerializedName("subreddit")
    val subreddit: String?,
    @SerializedName("subreddit_id")
    val subredditId: String?,
    @SerializedName("subreddit_name_prefixed")
    val subredditNamePrefixed: String?,
    @SerializedName("subreddit_subscribers")
    val subredditSubscribers: Int?,
    @SerializedName("subreddit_type")
    val subredditType: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("thumbnail_height")
    val thumbnailHeight: Int?,
    @SerializedName("thumbnail_width")
    val thumbnailWidth: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("total_awards_received")
    val totalAwardsReceived: Int?,
    @SerializedName("ups")
    val ups: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("visited")
    val visited: Boolean?,
    @SerializedName("whitelist_status")
    val whitelistStatus: String?,
    @SerializedName("wls")
    val wls: Int?
)