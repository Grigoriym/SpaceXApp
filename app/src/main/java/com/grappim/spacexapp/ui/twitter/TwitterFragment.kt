package com.grappim.spacexapp.ui.twitter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.recyclerview.TwitterAdapter
import com.grappim.spacexapp.ui.FullScreenImageActivity
import com.grappim.spacexapp.util.*
import kotlinx.android.synthetic.main.fragment_twitter.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import retrofit2.Response
import timber.log.Timber

class TwitterFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private lateinit var uAdapter: TwitterAdapter
  private val viewModelFactory: TwitterViewModelFactory by instance()
  private val viewModel by viewModels<TwitterViewModel> { viewModelFactory }

  private val observerWithResponse = Observer<Response<List<UserTimelineModel>>> {
    Timber.d("TwitterFragment - observer")
    pbTwitter.gone()
    if (it.isSuccessful) {
      it.body()?.let { items -> uAdapter.loadItems(items) }
    } else {
      srlTwitter.showSnackbar(getString(R.string.error_retrieving_data))
//      findNavController().popBackStack()
    }
    rvTwitter.scheduleLayoutAnimation()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_twitter, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("TwitterFragment - onViewCreated")
    viewModel.apply {
      userTimeline.observe(this@TwitterFragment, observerWithResponse)
    }

    bindAdapter()
    getData()
    srlTwitter.setOnRefreshListener {
      getData()
      srlTwitter.isRefreshing = false
    }
  }

  private fun getData() {
    pbTwitter.show()
    viewModel.getUserTimeline()
  }

  private fun bindAdapter() {
    uAdapter = TwitterAdapter(
      onClick = {
        startBrowser("$TWITTER_FOR_BROWSER_URI${it.idStr}")
      },
      onImageClick = {
        context?.launchActivity<FullScreenImageActivity> {
          putExtra("asd", it.extendedEntities?.media?.get(0)?.mediaUrlHttps)
        }
      }
    )
    rvTwitter.apply {
      layoutManager = LinearLayoutManager(context)
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = uAdapter
    }
  }

}
