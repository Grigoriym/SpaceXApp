package com.grappim.spacexapp.pagination

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.getTwitterDate
import com.grappim.spacexapp.util.inflateLayout
import com.grappim.spacexapp.util.roundCorners
import kotlinx.android.synthetic.main.layout_twitter_item.view.*

class TwitterPaginationAdapter(
  val onClick: (UserTimelineModel) -> Unit,
  val onImageClick: (UserTimelineModel) -> Unit
) : PagedListAdapter<UserTimelineModel,
    TwitterPaginationViewHolder>(MY_DIFF_UTIL) {

  companion object {
    val MY_DIFF_UTIL = object : DiffUtil.ItemCallback<UserTimelineModel>() {
      override fun areItemsTheSame(
        oldItem: UserTimelineModel,
        newItem: UserTimelineModel
      ): Boolean =
        oldItem == newItem

      override fun areContentsTheSame(
        oldItem: UserTimelineModel,
        newItem: UserTimelineModel
      ): Boolean =
        oldItem.id == newItem.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwitterPaginationViewHolder =
    TwitterPaginationViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_twitter_item, parent)
    )

  override fun onBindViewHolder(holder: TwitterPaginationViewHolder, position: Int) {
    holder.apply {
      userTimelineModel = getItem(position)
      itemView.setOnClickListener { onClick(getItem(position)!!) }
      GlideApp.with(profileImage.context)
        .load(getItem(position)?.user?.profileImageUrlHttps)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .apply(RequestOptions().placeholder(R.drawable.glide_placeholder).centerCrop())
        .into(profileImage)

      if (getItem(position)?.extendedEntities?.media?.get(0) != null) {
        GlideApp.with(mediaImage.context)
          .load(getItem(position)?.extendedEntities?.media?.get(0)?.mediaUrlHttps)
          .transition(DrawableTransitionOptions.withCrossFade())
          .centerCrop()
          .roundCorners(16)
          .into(mediaImage)
        mediaImage.setOnClickListener { onImageClick(getItem(position)!!) }
      }
    }
  }
}

class TwitterPaginationViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  val profileImage: ImageView = view.ivTwitterItemProfileImage
  val mediaImage: ImageView = view.ivTwitterItemMedia
  var userTimelineModel: UserTimelineModel? = null
    set(value) {
      field = value
      view.apply {
        tvTwitterItemCreatedAt.text = getTwitterDate(value?.createdAt)
        tvTwitterItemScreenName.text = "@${value?.user?.screenName}"
        tvTwitterItemText.text = value?.fullText
        tvTwitterName.text = value?.user?.name
        if (value?.extendedEntities?.media?.get(0) == null) {
          mediaImage.visibility = View.GONE
        } else {
          mediaImage.visibility = View.VISIBLE
        }
      }
    }
}