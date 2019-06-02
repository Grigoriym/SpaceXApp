package com.grappim.spacexapp.ui.twitter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.pagination.TwitterPaginationAdapter
import com.grappim.spacexapp.ui.FullScreenImageActivity
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_twitter.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class TwitterFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private val viewModelFactory: TwitterViewModelFactory by instance()
  private val viewModel by viewModels<TwitterViewModel> { viewModelFactory }

  private lateinit var uAdapter: TwitterPaginationAdapter

  private val observer = Observer<PagedList<UserTimelineModel>> {
    Timber.d("TwitterFragment - observer")
    uAdapter.submitList(it)
    pbTwitter.gone()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_twitter, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("TwitterFragment - onViewCreated")
    viewModel.apply {
      timelines.observe(this@TwitterFragment, observer)
    }
    bindAdapter()
    getData()
    srlTwitter.setOnRefreshListener {
      getData()
      srlTwitter.isRefreshing = false
    }
  }

  private fun getData() {
    pbTwitter.show()
  }

  private fun bindAdapter() {
    uAdapter = TwitterPaginationAdapter(
      onClick = {
        startBrowser("$TWITTER_FOR_BROWSER_URI${it.idStr}")
      }, onImageClick = {
        context?.launchActivity<FullScreenImageActivity> {
          putExtra("asd", it.extendedEntities?.media?.get(0)?.mediaUrlHttps)
        }
      })

    rvTwitter.apply {
      layoutManager = LinearLayoutManager(context)
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = uAdapter
    }
  }
}