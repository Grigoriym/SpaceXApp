package com.grappim.spacexapp.ui.social_media.reddit

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
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getErrorState
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.extensions.startBrowser
import com.grappim.spacexapp.core.utils.REDDIT_FOR_BROWSER_URI
import kotlinx.android.synthetic.main.fragment_reddit.pbReddit
import kotlinx.android.synthetic.main.fragment_reddit.rvReddit
import kotlinx.android.synthetic.main.fragment_reddit.srlReddit
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class RedditFragment : Fragment(R.layout.fragment_reddit) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RedditViewModel by viewModels { viewModelFactory }

    private var searchJob: Job? = null

    private val rAdapter: RedditPaginationAdapter by lazy {
        RedditPaginationAdapter {
            startBrowser("$REDDIT_FOR_BROWSER_URI${it?.data?.permalink}")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getFragmentsComponent()
        component.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Timber.d("RedditFragment - onCreateOptionsMenu")
        menu.clear()
        inflater.inflate(R.menu.twitter_menu, menu)
        initMenu(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        Timber.d("RedditFragment - onPrepareOptionsMenu")
        val item: MenuItem? = menu.findItem(R.id.twitter_menu_spinner)
        item?.isVisible = true
        val item2: MenuItem? = menu.findItem(R.id.twitter_menu_refresh)
        item2?.isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.twitter_menu_refresh -> {
                rAdapter.refresh()
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
            arrayListOf("SpaceX", "Elon Musk", "SpaceXLounge")
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
                Timber.d("RedditFragment - onItemSelected - $position")
                when (position) {
                    0 -> {
                        search("SpaceX")
                    }
                    1 -> {
                        search("elonmusk")
                    }
                    2 -> {
                        search("SpaceXLounge")
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        bindAdapter()

        srlReddit.setOnRefreshListener {
            rAdapter.refresh()
            srlReddit.isRefreshing = false
        }

        initSearch()
    }

    private fun search(subreddit: String) {
        searchJob?.cancel()
        rAdapter.refresh()
        searchJob = lifecycleScope.launch {
            viewModel.search(subreddit)
                .collectLatest { rAdapter.submitData(it) }
        }
    }

    private fun initSearch() {
        lifecycleScope.launch {
            rAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { rvReddit.scrollToPosition(0) }
        }
    }

    private fun bindAdapter() {
        rAdapter.addLoadStateListener { loadState ->
            rvReddit.isVisible = loadState.source.refresh is LoadState.NotLoading
            pbReddit.isVisible = loadState.source.refresh is LoadState.Loading

            loadState.getErrorState()?.let {
                rvReddit.showSnackbar(
                    requireContext().getErrorMessage(it.error)
                )
            }
        }

        rvReddit.apply {
            layoutAnimation = AnimationUtils
                .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
            adapter = rAdapter
        }
    }
}
