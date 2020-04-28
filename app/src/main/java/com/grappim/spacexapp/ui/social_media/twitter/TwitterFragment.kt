package com.grappim.spacexapp.ui.social_media.twitter

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.SpaceXApplication
import com.grappim.spacexapp.core.extensions.*
import com.grappim.spacexapp.core.utils.*
import com.grappim.spacexapp.pagination.NetworkState
import com.grappim.spacexapp.pagination.twitter.TwitterPaginationAdapter
import com.grappim.spacexapp.ui.full_screen.FullScreenImageActivity
import com.grappim.spacexapp.ui.full_screen.FullScreenVideoActivity
import com.grappim.spacexapp.util.Failure
import kotlinx.android.synthetic.main.fragment_twitter.*
import timber.log.Timber
import javax.inject.Inject

class TwitterFragment : Fragment() {

  @Inject
  lateinit var viewModel: TwitterViewModel

  @Inject
  lateinit var viewModelFactory: TwitterViewModelFactory

  private lateinit var uAdapter: TwitterPaginationAdapter

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (requireActivity().application as SpaceXApplication).appComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_twitter, container, false)

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    Timber.d("TwitterFragment - onCreateOptionsMenu")
    menu.clear()
    inflater.inflate(R.menu.twitter_menu, menu)
    initMenu(menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onPrepareOptionsMenu(menu: Menu) {
    val item: MenuItem? = menu.findItem(R.id.twitter_menu_spinner)
    item?.isVisible = true
    val item2: MenuItem? = menu.findItem(R.id.twitter_menu_refresh)
    item2?.isVisible = true

    super.onPrepareOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.twitter_menu_refresh -> {
        viewModel.refresh()
      }
    }
    return super.onOptionsItemSelected(item)
  }

  private fun handleFailure(failure: Failure?) {
    when (failure) {
      is Failure.NetworkConnection -> renderFailure("Network Connection Error")
      is Failure.ServerError -> renderFailure("Server Error")
    }
  }

  private fun initMenu(menu: Menu) {
    val item: MenuItem? = menu.findItem(R.id.twitter_menu_spinner)
    val spinner = item?.actionView as? AppCompatSpinner

    val spinnerArrayAdapter: ArrayAdapter<String>? = ArrayAdapter(
      requireContext(),
      R.layout.layout_spinner_item,
      arrayListOf(SPACE_X, "Elon Musk")
    )
    spinner?.adapter = spinnerArrayAdapter
    spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {

      }

      override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
      ) {
        Timber.d("TwitterFragment - onItemSelected - $position")
        when (position) {
          0 -> {
            viewModel.setCurrentScreenName(SPACE_X)
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
    setHasOptionsMenu(true)

    viewModel.apply {
      tweets.observe(viewLifecycleOwner, Observer {
        uAdapter.submitList(it)
      })
      networkState.observe(viewLifecycleOwner, Observer {
        when (it) {
          NetworkState.LOADING -> pbTwitter.show()
          NetworkState.LOADED -> pbTwitter.gone()
        }
      })
      currentScreenName.observe(viewLifecycleOwner, Observer {
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

  fun renderFailure(failureText: String) {
    rvTwitter.showSnackbar(failureText)
    pbTwitter.gone()
    srlTwitter.isRefreshing = false
  }

  private fun bindAdapter() {
    uAdapter = TwitterPaginationAdapter(
      onClick = {
        startBrowser("$TWITTER_FOR_BROWSER_URI${it?.idStr}")
      },
      onImageClickS = { url, isVideo, videoDuration ->
        when (isVideo) {
          true -> {
            requireContext().launchActivity<FullScreenVideoActivity> {
              putExtra(PARCELABLE_TWITTER_VIDEO, url)
              putExtra(PARCELABLE_TWITTER_VIDEO_DURATION, videoDuration)
            }
          }
          false -> {
            requireContext().launchActivity<FullScreenImageActivity> {
              putExtra(PARCELABLE_TWITTER_IMAGES, url)
            }
          }
        }
      })

    rvTwitter.apply {
      layoutManager = LinearLayoutManager(requireContext())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
      adapter = uAdapter
    }
  }

  override fun onPause() {
//    setHasOptionsMenu(false)
    viewModelStore.clear()
    super.onPause()
  }
}