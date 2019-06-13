package com.grappim.spacexapp.ui.social_media.twitter

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.pagination.NetworkState
import com.grappim.spacexapp.pagination.TwitterPaginationAdapter
import com.grappim.spacexapp.ui.full_screen.FullScreenImageActivity
import com.grappim.spacexapp.ui.full_screen.FullScreenVideoActivity
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_twitter.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class TwitterFragment : Fragment(), KoinComponent {

  private val viewModelFactory: TwitterViewModelFactory by inject()
  private val viewModel by viewModels<TwitterViewModel> { viewModelFactory }
  private lateinit var uAdapter: TwitterPaginationAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_twitter, container, false)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    Timber.d("TwitterFragment - onCreateOptionsMenu")
    menu.clear()
    activity?.menuInflater?.inflate(R.menu.twitter_menu, menu)
  }

  override fun onPrepareOptionsMenu(menu: Menu) {
    super.onPrepareOptionsMenu(menu)
    initMenu(menu)
  }

  override fun onDestroy() {
    super.onDestroy()
    Timber.d("TwitterFragment - onDestroy")
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.twitter_menu_refresh -> {
        viewModel.refresh()
      }
    }
    return super.onOptionsItemSelected(item)
  }

  private fun initMenu(menu:Menu){
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
        Timber.d("TwitterFragment - onNothingSelected")
      }

      override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?, position: Int,
        id: Long
      ) {
        Timber.d("TwitterFragment - onItemSelected - $position")
        when (position) {
          0 -> {
            viewModel.setCurrentScreenName("SpaceX")
          }
          1 -> {
            viewModel.setCurrentScreenName("elonmusk")
          }
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("TwitterFragment - onViewCreated")
//    setHasOptionsMenu(true)

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
      currentScreenName.observe(this@TwitterFragment, Observer {
        showTweets()
      })
      onFailure(failure, ::handleFailure)
    }

    bindAdapter()

    srlTwitter.setOnRefreshListener {
      viewModel.refresh()
      srlTwitter.isRefreshing = false
    }
  }

  fun handleFailure(failure: Failure?) {
    when (failure) {
      is Failure.NetworkConnection -> renderFailure("Network Connection Error")
      is Failure.ServerError -> renderFailure("Server Error")
    }
  }

  fun renderFailure(failureText: String) {
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