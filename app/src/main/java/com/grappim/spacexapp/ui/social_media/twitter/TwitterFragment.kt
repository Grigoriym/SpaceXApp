package com.grappim.spacexapp.ui.social_media.twitter

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.pagination.NetworkState
import com.grappim.spacexapp.pagination.TwitterPaginationAdapter
import com.grappim.spacexapp.ui.FullScreenImageActivity
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_twitter.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class TwitterFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private val viewModelFactory: TwitterViewModelFactory by instance()
  private val viewModel by viewModels<TwitterViewModel> { viewModelFactory }

  private lateinit var uAdapter: TwitterPaginationAdapter

  private val observer = Observer<PagedList<UserTimelineModel>> {
    Timber.d("TwitterFragment - observer")
    uAdapter.submitList(it)
  }

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

    val spinnerArrayAdaper = ArrayAdapter<String>(
      context!!,
      android.R.layout.simple_spinner_dropdown_item,
      arrayListOf("One", "Two")
    )
    spinner.adapter = spinnerArrayAdaper
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
      override fun onNothingSelected(parent: AdapterView<*>?) {
      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId){

    }
    return super.onOptionsItemSelected(item)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("TwitterFragment - onViewCreated")
    setHasOptionsMenu(true)

    viewModel.apply {
      tweets.observe(this@TwitterFragment, observer)
      networkState.observe(this@TwitterFragment, Observer {

      })
      refreshState.observe(this@TwitterFragment, Observer {
        pbTwitter.showIf { it == NetworkState.LOADING }
      })
    }
    bindAdapter()
    getData()
    srlTwitter.setOnRefreshListener {
      getData()
      viewModel.refresh()
      srlTwitter.isRefreshing = false
    }
  }

  private fun getData() {
    viewModel.showTweets("Spacex")
  }

  private fun bindAdapter() {
    uAdapter = TwitterPaginationAdapter(
      onClick = {
        startBrowser("$TWITTER_FOR_BROWSER_URI${it.idStr}")
      }, onImageClickS = {
        context?.launchActivity<FullScreenImageActivity> {
          putExtra(PARCELABLE_TWITTER_IMAGES, it)
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