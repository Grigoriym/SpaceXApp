package com.grappim.spacexapp.ui.launches.upcoming

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getErrorMessage
import com.grappim.spacexapp.core.extensions.getFragmentsComponent
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.api.model.launches.LaunchModel
import com.grappim.spacexapp.ui.MainActivity
import com.grappim.spacexapp.ui.launches.LaunchesAdapter
import kotlinx.android.synthetic.main.fragment_upcoming_launches.pbUpcomingLaunches
import kotlinx.android.synthetic.main.fragment_upcoming_launches.rvUpcomingLaunches
import kotlinx.android.synthetic.main.fragment_upcoming_launches.srlUpcomingLaunches
import timber.log.Timber
import javax.inject.Inject

class UpcomingLaunchesFragment : Fragment(R.layout.fragment_upcoming_launches) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: UpcomingLaunchesViewModel by viewModels { viewModelFactory }

    private val launchesAdapter: LaunchesAdapter by lazy {
        LaunchesAdapter {
            (requireActivity() as? MainActivity)?.showLaunchDetails(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component = context.getFragmentsComponent()
        component.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Timber.d("UpcomingLaunchesFragment - onCreateOptionsMenu")
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        initSearchView(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item3: MenuItem? = menu.findItem(R.id.searchMenu)
        item3?.isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

    private fun initSearchView(menu: Menu) {
        val searchView: SearchView? = menu.findItem(R.id.searchMenu).actionView as? SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                launchesAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                launchesAdapter.filter.filter(newText)
                return true
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("UpcomingLaunchesFragment - onViewCreated")
        setHasOptionsMenu(true)

        viewModel.apply {
            upcomingLaunches.observe(viewLifecycleOwner, ::renderLaunches)
        }

        bindAdapter()
        getData()

        srlUpcomingLaunches.setOnRefreshListener {
            getData()
            srlUpcomingLaunches.isRefreshing = false
        }
    }

    private fun getData() {
        viewModel.loadAllLaunches()
    }

    private fun bindAdapter() {
        rvUpcomingLaunches.apply {
            addItemDecoration(MarginItemDecorator())
            layoutAnimation = AnimationUtils
                .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
            adapter = launchesAdapter
        }
    }

    private fun renderLaunches(resource: Resource<List<LaunchModel>>) {
        pbUpcomingLaunches.showOrGone(resource is Resource.Loading)
        when (resource) {
            is Resource.Error -> {
                showError(resource.exception)
            }
            is Resource.Success -> {
                launchesAdapter.loadItems(resource.data)
                rvUpcomingLaunches.scheduleLayoutAnimation()
            }
        }
    }

    private fun showError(throwable: Throwable) {
        rvUpcomingLaunches.showSnackbar(requireContext().getErrorMessage(throwable))
    }
}
