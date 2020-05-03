package com.grappim.spacexapp.ui.launches.completed

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.*
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.ui.launches.LaunchesAdapter
import com.grappim.spacexapp.core.view.MarginItemDecorator
import com.grappim.spacexapp.ui.MainActivity
import com.grappim.spacexapp.util.Failure
import kotlinx.android.synthetic.main.fragment_completed_launches.*
import timber.log.Timber
import javax.inject.Inject

class CompletedLaunchesFragment : Fragment() {

  @Inject
  lateinit var viewModel: CompletedLaunchesViewModel

  private lateinit var lAdapter: LaunchesAdapter

  override fun onAttach(context: Context) {
    super.onAttach(context)
    getAppComponent().inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_completed_launches, container, false)

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
        lAdapter.filter.filter(query)
        return true
      }

      override fun onQueryTextChange(newText: String?): Boolean {
        lAdapter.filter.filter(newText)
        return true
      }
    })
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("CompletedLaunchesFragment - onViewCreated")
    setHasOptionsMenu(true)

    viewModel.apply {
      onObserve(pastLaunches, ::renderLaunches)
      onFailure(failure, ::handleFailure)
    }

    bindAdapter()
    getData()

    srlCompletedLaunches.setOnRefreshListener {
      getData()
      srlCompletedLaunches.isRefreshing = false
    }
  }

  private fun getData() {
    pbCompletedLaunches.show()
    viewModel.loadPastLaunches()
  }

  private fun handleFailure(failure: Failure?) {
    when (failure) {
      is Failure.NetworkConnection -> renderFailure("Network Connection Error")
      is Failure.ServerError -> renderFailure("Server Error")
    }
  }

  private fun bindAdapter() {
    lAdapter = LaunchesAdapter {
      (requireActivity() as? MainActivity)?.showLaunchDetails(it)
    }
    rvCompletedLaunches.apply {
      layoutManager = LinearLayoutManager(requireContext())
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
      adapter = lAdapter
    }
  }

  private fun renderFailure(failureText: String) {
    rvCompletedLaunches.showSnackbar(failureText)
    pbCompletedLaunches.gone()
    srlCompletedLaunches.isRefreshing = false
  }

  private fun renderLaunches(launches: List<LaunchModel>?) {
    launches?.let {
      lAdapter.loadItems(it)
    }
    pbCompletedLaunches.gone()
    rvCompletedLaunches.scheduleLayoutAnimation()
  }

}
