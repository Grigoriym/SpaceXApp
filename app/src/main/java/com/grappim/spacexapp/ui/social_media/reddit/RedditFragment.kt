package com.grappim.spacexapp.ui.social_media.reddit

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
import com.grappim.spacexapp.pagination.reddit.RedditPaginationAdapter
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_reddit.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class RedditFragment : Fragment(), KoinComponent {

  private val viewModelFactory: RedditViewModelFactory  by inject()
  private val viewModel by viewModels<RedditViewModel> { viewModelFactory }
  private lateinit var rAdapter: RedditPaginationAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_reddit, container, false)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
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

  fun renderFailure(failureText: String) {
    rvReddit.showSnackbar(failureText)
    pbReddit.gone()
    srlReddit.isRefreshing = false
  }

  private fun initMenu(menu: Menu) {
    val item: MenuItem? = menu.findItem(R.id.twitter_menu_spinner)
    val spinner = item?.actionView as? AppCompatSpinner

    val spinnerArrayAdapter: ArrayAdapter<String>? = ArrayAdapter(
      context!!,
      R.layout.layout_spinner_item,
      arrayListOf("SpaceX", "Elon Musk")
    )
    spinner?.adapter = spinnerArrayAdapter
    spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(parent: AdapterView<*>?) {
        Timber.d("RedditFragment - onNothingSelected")
      }

      override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
      ) {

        Timber.d("RedditFragment - onItemSelected - $position")
        when (position) {
          0 -> {
            viewModel.setCurrentSubreddit("SpaceX")
          }
          1 -> {
            viewModel.setCurrentSubreddit("elonmusk")
          }
        }
      }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(true)

    viewModel.apply {
      posts.observe(this@RedditFragment, Observer {
        rAdapter.submitList(it)
      })
      networkState.observe(this@RedditFragment, Observer {
        when (it) {
          NetworkState.LOADING -> pbReddit.show()
          NetworkState.LOADED -> pbReddit.gone()
        }
      })
      currentSubreddit.observe(this@RedditFragment, Observer {
        showPosts()
      })
      onFailure(failure, ::handleFailure)
    }

    bindAdapter()

    srlReddit.setOnRefreshListener {
      viewModel.refresh()
      srlReddit.isRefreshing = false
    }
  }

  private fun bindAdapter() {
    rAdapter = RedditPaginationAdapter {
      startBrowser("$REDDIT_FOR_BROWSER_URI${it?.permalink}")
    }
    rvReddit.apply {
      layoutManager = LinearLayoutManager(context)
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = rAdapter
    }
  }
}
