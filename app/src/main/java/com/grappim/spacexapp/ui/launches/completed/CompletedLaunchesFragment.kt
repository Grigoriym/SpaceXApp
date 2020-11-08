package com.grappim.spacexapp.ui.launches.completed

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
import com.grappim.spacexapp.core.extensions.show
import com.grappim.spacexapp.core.extensions.showOrGone
import com.grappim.spacexapp.core.extensions.showSnackbar
import com.grappim.spacexapp.core.functional.Resource
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.ui.MainActivity
import com.grappim.spacexapp.ui.launches.LaunchesAdapter
import com.grappim.spacexapp.util.Failure
import kotlinx.android.synthetic.main.fragment_completed_launches.pbCompletedLaunches
import kotlinx.android.synthetic.main.fragment_completed_launches.rvCompletedLaunches
import kotlinx.android.synthetic.main.fragment_completed_launches.srlCompletedLaunches
import timber.log.Timber
import javax.inject.Inject

class CompletedLaunchesFragment : Fragment(R.layout.fragment_completed_launches) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CompletedLaunchesViewModel by viewModels { viewModelFactory }

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
        Timber.d("CompletedLaunchesFragment - onCreateOptionsMenu")
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
        Timber.d("CompletedLaunchesFragment - onViewCreated")
        setHasOptionsMenu(true)

        viewModel.apply {
            pastLaunches.observe(viewLifecycleOwner, ::renderLaunches)
        }

        bindAdapter()
        getData()

        srlCompletedLaunches.setOnRefreshListener {
            getData()
            srlCompletedLaunches.isRefreshing = false
        }
    }

    private fun getData() {
        viewModel.loadPastLaunches()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure("Network Connection Error")
            is Failure.ServerError -> renderFailure("Server Error")
        }
    }

    private fun bindAdapter() {
        rvCompletedLaunches.apply {
            addItemDecoration(MarginItemDecorator())
            layoutAnimation = AnimationUtils
                .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
            adapter = launchesAdapter
        }
    }

    private fun renderFailure(failureText: String) {
        rvCompletedLaunches.showSnackbar(failureText)
        pbCompletedLaunches.gone()
        srlCompletedLaunches.isRefreshing = false
    }

    private fun renderLaunches(resource: Resource<List<LaunchModel>>) {
        pbCompletedLaunches.showOrGone(resource is Resource.Loading)
        when (resource) {
            is Resource.Success -> {
                launchesAdapter.loadItems(resource.data)
                rvCompletedLaunches.scheduleLayoutAnimation()
            }
            is Resource.Error -> {
                showError(resource.exception)
            }
        }
    }

    private fun showError(throwable: Throwable) {
        rvCompletedLaunches.showSnackbar(requireContext().getErrorMessage(throwable))
    }

}
