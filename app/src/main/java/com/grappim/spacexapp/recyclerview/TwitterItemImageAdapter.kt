package com.grappim.spacexapp.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.inflateLayout
import com.grappim.spacexapp.core.extensions.px
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.core.utils.GlideApp
import com.grappim.spacexapp.core.utils.roundCorners
import kotlinx.android.synthetic.main.layout_twitter_item_image.view.*

class TwitterItemImageAdapter(
  val onImageClick: (String) -> Unit
) : RecyclerView.Adapter<TwitterItemImageViewHolder>() {

  private var items = mutableListOf<String>()
  private var hashMap = mutableMapOf<String, Boolean>()

  override fun onCreateViewHolder(
    parent: ViewGroup, viewType: Int
  ): TwitterItemImageViewHolder = TwitterItemImageViewHolder(
    parent.context.inflateLayout(R.layout.layout_twitter_item_image)
  )

  override fun onBindViewHolder(holder: TwitterItemImageViewHolder, position: Int) {
    holder.apply {
      val lp = twitterImage.layoutParams
      when (items.size) {
        1, 2 -> {
          lp.height = 200.px
        }
        else -> {
          lp.height = 100.px
        }
      }
      twitterImage.layoutParams = lp
      hashMap.forEach {
        if (it.value) {
          videoLabel.show()
        }
      }

      GlideApp.with(cl.context).load(items[position])
        .transition(DrawableTransitionOptions.withCrossFade()).centerCrop().roundCorners(16)
        .into(twitterImage)
      twitterImage.setSafeOnClickListener {
        onImageClick(items[position])
      }
    }
  }

  override fun getItemCount(): Int = items.size

  fun loadItems(newHashMap: MutableMap<String, Boolean>) {
    this.hashMap = newHashMap
    hashMap.forEach {
      items.add(it.key)
    }
    notifyDataSetChanged()
  }
}

class TwitterItemImageViewHolder(
  view: View
) : RecyclerView.ViewHolder(view) {

  val cl: ConstraintLayout = view.clTwitterItemImage
  val twitterImage: ImageView = view.ivTwitterItemImage
  val videoLabel: ImageView = view.imageVideoLabel

}