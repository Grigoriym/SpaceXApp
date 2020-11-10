package com.grappim.spacexapp.ui.social_media.twitter

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.launchActivity
import com.grappim.spacexapp.core.extensions.onFailure
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.extensions.startBrowser
import com.grappim.spacexapp.core.utils.PARCELABLE_TWITTER_IMAGES
import com.grappim.spacexapp.core.utils.PARCELABLE_TWITTER_VIDEO
import com.grappim.spacexapp.core.utils.PARCELABLE_TWITTER_VIDEO_DURATION
import com.grappim.spacexapp.core.utils.SPACE_X
import com.grappim.spacexapp.core.utils.TWITTER_FOR_BROWSER_URI
import com.grappim.spacexapp.pagination.NetworkState
import com.grappim.spacexapp.ui.full_screen.FullScreenImageActivity
import com.grappim.spacexapp.ui.full_screen.FullScreenVideoActivity
import com.grappim.spacexapp.util.Failure
import kotlinx.android.synthetic.main.fragment_twitter.pbTwitter
import kotlinx.android.synthetic.main.fragment_twitter.rvTwitter
import kotlinx.android.synthetic.main.fragment_twitter.srlTwitter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class TwitterFragment : Fragment(R.layout.fragment_twitter) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: TwitterViewModel by viewModels { viewModelFactory }

    private lateinit var tweetsAdapter: TwitterPaginationAdapter

    private var searchJob: Job? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getFragmentsComponent()
        component.inject(this)
    }

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
                tweetsAdapter.refresh()
            }
        }
        return super.onOptionsItemSelected(item)
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
                        search(SPACE_X)
                    }
                    1 -> {
                        search("elonmusk")
                    }
                }
            }
        }
    }

    private fun search(screenName: String) {
        searchJob?.cancel()
        tweetsAdapter.refresh()
        searchJob = lifecycleScope.launch {
            viewModel.search(screenName)
                .collectLatest {
                    tweetsAdapter.submitData(it)
                }
        }
    }

    private fun initSearch() {
        lifecycleScope.launch {
            tweetsAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { rvTwitter.scrollToPosition(0) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("TwitterFragment - onViewCreated")
        setHasOptionsMenu(true)

//        viewModel.apply {
//            tweets.observe(viewLifecycleOwner, Observer {
//              uAdapter.submitList(it)
//            })
//            networkState.observe(viewLifecycleOwner, Observer {
//              when (it) {
//                NetworkState.LOADING -> pbTwitter.show()
//                NetworkState.LOADED -> pbTwitter.gone()
//              }
//            })
//            currentScreenName.observe(viewLifecycleOwner, Observer {
//              showTweets()
//            })
//        }

        bindAdapter()

        srlTwitter.setOnRefreshListener {
//            viewModel.refresh()
            tweetsAdapter.refresh()
            srlTwitter.isRefreshing = false
        }
        initSearch()
    }

    fun renderFailure(failureText: String) {
        rvTwitter.showSnackbar(failureText)
        pbTwitter.gone()
        srlTwitter.isRefreshing = false
    }

    private fun bindAdapter() {
        tweetsAdapter = TwitterPaginationAdapter(
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
        tweetsAdapter.addLoadStateListener {loadState->
            pbTwitter.isVisible = loadState.source.refresh is LoadState.Loading
        }

        rvTwitter.apply {
            layoutAnimation = AnimationUtils
                .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
            adapter = tweetsAdapter
        }
    }

    override fun onPause() {
//    setHasOptionsMenu(false)
        viewModelStore.clear()
        super.onPause()
    }
}