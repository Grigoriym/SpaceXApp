package com.grappim.spacexapp.ui.social_media.twitter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.inflate
import com.grappim.spacexapp.core.extensions.px
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.extensions.show
import kotlinx.android.synthetic.main.layout_twitter_item_image.view.clTwitterItemImage
import kotlinx.android.synthetic.main.layout_twitter_item_image.view.imageVideoLabel
import kotlinx.android.synthetic.main.layout_twitter_item_image.view.ivTwitterItemImage

class TwitterItemImageAdapter(
    val onImageClick: (String) -> Unit
) : RecyclerView.Adapter<TwitterItemImageAdapter.TwitterItemImageViewHolder>() {

    private var items = mutableListOf<String>()
    private var hashMap = mutableMapOf<String, Boolean>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TwitterItemImageViewHolder =
        TwitterItemImageViewHolder(
            parent.inflate(R.layout.layout_twitter_item_image)
        )

    override fun onBindViewHolder(holder: TwitterItemImageViewHolder, position: Int) {
        holder.apply {
            itemView.apply {
                val lp = ivTwitterItemImage.layoutParams
                when (items.size) {
                    1, 2 -> {
                        lp.height = 200.px
                    }
                    else -> {
                        lp.height = 100.px
                    }
                }
                ivTwitterItemImage.layoutParams = lp
                hashMap.forEach {
                    if (it.value) {
                        imageVideoLabel.show()
                    }
                }

                imageVideoLabel.load(items[position])

                clTwitterItemImage.setSafeOnClickListener {
                    onImageClick(items[position])
                }
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

    class TwitterItemImageViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

