package com.grappim.spacexapp.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

class TwitterAdapter(
  val onClick: (UserTimelineModel) -> Unit,
  val onImageClick: (UserTimelineModel) -> Unit
) : RecyclerView.Adapter<TwitterViewHolder>() {

  private var items: List<UserTimelineModel> = emptyList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwitterViewHolder =
    TwitterViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_twitter_item, parent)
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: TwitterViewHolder, position: Int) {
    holder.apply {
      userTimelineModel = items[position]
      itemView.setOnClickListener { onClick(items[position]) }
      GlideApp.with(profileImage.context)
        .load(items[position].user?.profileImageUrlHttps)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .apply(RequestOptions().placeholder(R.drawable.glide_placeholder).centerCrop())
        .into(profileImage)

      if (items[position].extendedEntities?.media?.get(0) != null) {
        GlideApp.with(mediaImage.context)
          .load(items[position].extendedEntities?.media?.get(0)?.mediaUrlHttps)
          .transition(DrawableTransitionOptions.withCrossFade())
          .centerCrop()
          .roundCorners(16)
          .into(mediaImage)
        mediaImage.setOnClickListener { onImageClick(items[position]) }
      }
    }
  }

  fun loadItems(newItems: List<UserTimelineModel>) {
    items = newItems
    notifyDataSetChanged()
  }
}

class TwitterViewHolder(
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
        }else{
          mediaImage.visibility = View.VISIBLE
        }
      }
    }
}