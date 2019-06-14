package com.grappim.spacexapp.ui.launches

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.grappim.spacexapp.R
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_launches.*
import timber.log.Timber

class LaunchesFragment : BaseFragment () {

  override fun renderFailure(failureText: String) {
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_launches, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("LaunchesFragment - onViewCreated")
    val lfpa = LaunchesFragmentPagerAdapter(activity?.supportFragmentManager)
    vpLaunches.adapter = lfpa
    tlLaunches.setupWithViewPager(vpLaunches)
  }

}
