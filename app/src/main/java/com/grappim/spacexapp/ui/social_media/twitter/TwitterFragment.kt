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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.grappim.spacexapp.R
import com.grappim.spacexapp.api.model.twitter.TweetModel
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getErrorState
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.launchActivity
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.extensions.startBrowser
import com.grappim.spacexapp.core.utils.ELON_MUSK
import com.grappim.spacexapp.core.utils.ELON_MUSK_SCREEN_NAME
import com.grappim.spacexapp.core.utils.PARCELABLE_TWITTER_IMAGES
import com.grappim.spacexapp.core.utils.PARCELABLE_TWITTER_VIDEO
import com.grappim.spacexapp.core.utils.PARCELABLE_TWITTER_VIDEO_DURATION
import com.grappim.spacexapp.core.utils.SPACE_X
import com.grappim.spacexapp.core.utils.TWITTER_FOR_BROWSER_URI
import com.grappim.spacexapp.ui.full_screen.FullScreenImageActivity
import com.grappim.spacexapp.ui.full_screen.FullScreenVideoActivity
import kotlinx.android.synthetic.main.fragment_twitter.pbTwitter
import kotlinx.android.synthetic.main.fragment_twitter.recyclerTwitter
import kotlinx.android.synthetic.main.fragment_twitter.srlTwitter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class TwitterFragment : Fragment(R.layout.fragment_twitter), TwitterAdapterClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: TwitterViewModel by viewModels { viewModelFactory }

    private val tweetsAdapter: TwitterPaginationAdapter by lazy {
        TwitterPaginationAdapter(this)
    }

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
            arrayListOf(SPACE_X, ELON_MUSK)
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
                        search(ELON_MUSK_SCREEN_NAME)
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
                .collect { recyclerTwitter.scrollToPosition(0) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("TwitterFragment - onViewCreated")
        setHasOptionsMenu(true)

        bindAdapter()

        srlTwitter.setOnRefreshListener {
            tweetsAdapter.refresh()
            srlTwitter.isRefreshing = false
        }
        initSearch()
    }

    private fun bindAdapter() {
        tweetsAdapter.addLoadStateListener { loadState ->
            recyclerTwitter.isVisible = loadState.source.refresh is LoadState.NotLoading
            pbTwitter.isVisible = loadState.source.refresh is LoadState.Loading

            loadState.getErrorState()?.let {
                recyclerTwitter.showSnackbar(
                    requireContext()
                        .getErrorMessage(it.error)
                )
            }
        }

        recyclerTwitter.apply {
            layoutAnimation = AnimationUtils
                .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
            adapter = tweetsAdapter
        }
    }

    override fun onTweetClick(tweet: TweetModel) {
        startBrowser("$TWITTER_FOR_BROWSER_URI${tweet.idStr}")
    }

    override fun onImageClick(tweet: TweetModel) {
        var isVideo = false
        var url: String? = ""
        var videoDuration: Int? = null
        if (tweet.extendedEntities?.media?.get(0)?.type == TWITTER_VIDEO_TYPE) {
            val videoInfo = tweet.extendedEntities.media[0].videoInfo
            val videoVariants = videoInfo.variants
            videoVariants?.find {
                it.bitrate == TwitterVideoBitRates.BITRATE_2176000.bitrate
            }?.let {
                url = it.url
                isVideo = true
                videoDuration = videoInfo.durationMillis
            }
        }
        when (isVideo) {
            true -> {
                requireContext().launchActivity<FullScreenVideoActivity> {
                    putExtra(PARCELABLE_TWITTER_VIDEO, url)
                    putExtra(PARCELABLE_TWITTER_VIDEO_DURATION, videoDuration)
                }
            }
            false -> {
                requireContext().launchActivity<FullScreenImageActivity> {
                    putExtra(PARCELABLE_TWITTER_IMAGES, tweet.extendedEntities!!.media!![0].mediaUrlHttps!!)
                }
            }
        }
    }
}