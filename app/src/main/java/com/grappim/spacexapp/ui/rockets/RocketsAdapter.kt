package com.grappim.spacexapp.ui.rockets

import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.inflate
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.core.utils.GlideApp
import com.grappim.spacexapp.model.rocket.RocketModel
import kotlinx.android.synthetic.main.layout_rocket_item.view.*

class RocketsAdapter(
  private inline val onClick: (RocketModel) -> Unit
) : RecyclerView.Adapter<RocketsAdapter.RocketsViewHolder>() {

  private var items: List<RocketModel> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): RocketsViewHolder =
    RocketsViewHolder(parent.inflate(R.layout.layout_rocket_item))

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(
    holder: RocketsViewHolder,
    position: Int
  ) {
    holder.apply {
      itemView.apply {
        val item = items[position]
        btnRocketItem.setSafeOnClickListener {
          onClick(items[position])
        }

        tvRocketItemRocketName.text = item.rocketName
        tvRocketItemDescription.text = item.description

        GlideApp.with(btnRocketItem.context)
          .load(items[position].flickrImages?.random())
          .transition(DrawableTransitionOptions.withCrossFade())
          .centerCrop()
          .apply(RequestOptions().placeholder(R.drawable.glide_placeholder).centerCrop())
          .into(ivRocketItem)

        itemView.setOnClickListener {
          if (tvRocketItemDescription.isShown) {
            tvRocketItemDescription.startAnimation(
              AnimationUtils.loadAnimation(
                itemView.context,
                R.anim.slide_up
              )
            )
            val countDownTimeStatic = object : CountDownTimer(200, 16) {
              override fun onTick(millisUntilFinished: Long) {}

              override fun onFinish() {
                tvRocketItemDescription.gone()
              }
            }
            countDownTimeStatic.start()
          } else {
            tvRocketItemDescription.apply {
              show()
              startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_down))
            }
          }
        }
      }
    }
  }

  fun loadItems(newItems: List<RocketModel>) {
    items = newItems
    notifyDataSetChanged()
  }

  class RocketsViewHolder(view: View) : RecyclerView.ViewHolder(view)

}