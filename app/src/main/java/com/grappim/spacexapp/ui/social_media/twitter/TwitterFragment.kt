package com.grappim.spacexapp.ui.social_media.twitter

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.pagination.NetworkState
import com.grappim.spacexapp.pagination.TwitterPaginationAdapter
import com.grappim.spacexapp.ui.FullScreenImageActivity
import com.grappim.spacexapp.ui.FullScreenVideoActivity
import com.grappim.spacexapp.ui.SharedFragment
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_twitter.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class TwitterFragment : SharedFragment(), KoinComponent {

  private val viewModelFactory: TwitterViewModelFactory by inject()
  private val viewModel by viewModels<TwitterViewModel> { viewModelFactory }
  private lateinit var uAdapter: TwitterPaginationAdapter

  private var currentScreenName: String? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_twitter, container, false)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    menu.clear()
    activity?.menuInflater?.inflate(R.menu.twitter_menu, menu)
    val item = menu.findItem(R.id.twitter_menu_spinner)
    val spinner = item.actionView as AppCompatSpinner

    val spinnerArrayAdapter = ArrayAdapter<String>(
      context!!,
      android.R.layout.simple_spinner_dropdown_item,
      arrayListOf("SpaceX", "Elon Musk")
    )
    spinner.adapter = spinnerArrayAdapter
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {
      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
          0 -> {
            currentScreenName = "SpaceX"
            getData()
          }
          1 -> {
            currentScreenName = "elonmusk"
            getData()
          }
        }
      }
    }
  }

  override fun onPrepareOptionsMenu(menu: Menu) {

  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.twitter_menu_refresh -> {
        getData()
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("TwitterFragment - onViewCreated")
    setHasOptionsMenu(true)

    viewModel.apply {
      tweets.observe(this@TwitterFragment, Observer {
        uAdapter.submitList(it)
      })
      networkState.observe(this@TwitterFragment, Observer {
        when (it) {
          NetworkState.LOADING -> pbTwitter.show()
          NetworkState.LOADED -> pbTwitter.gone()
        }
      })
    }

    bindAdapter()
    getData()

    srlTwitter.setOnRefreshListener {
      getData()
      srlTwitter.isRefreshing = false
    }
  }

  private fun getData() {
    viewModel.showTweets(currentScreenName ?: "SpaceX")
    viewModel.refresh()
  }

  override fun renderFailure(failureText: String) {
    rvTwitter.showSnackbar(failureText)
    pbTwitter.gone()
    srlTwitter.isRefreshing = false
  }

  private fun bindAdapter() {
    uAdapter = TwitterPaginationAdapter(
      onClick = {
        startBrowser("$TWITTER_FOR_BROWSER_URI${it.idStr}")
      },
      onImageClickS = { url, isVideo, videoDuration ->
        when (isVideo) {
          true -> {
            context?.launchActivity<FullScreenVideoActivity> {
              putExtra(PARCELABLE_TWITTER_VIDEO, url)
              putExtra(PARCELABLE_TWITTER_VIDEO_DURATION, videoDuration)
            }
          }
          false -> {
            context?.launchActivity<FullScreenImageActivity> {
              putExtra(PARCELABLE_TWITTER_IMAGES, url)
            }
          }
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