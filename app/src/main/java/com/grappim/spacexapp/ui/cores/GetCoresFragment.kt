package com.grappim.spacexapp.ui.cores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import com.grappim.spacexapp.recyclerview.adapters.CoresAdapter
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import com.grappim.spacexapp.util.showSnackbar
import kotlinx.android.synthetic.main.fragment_get_cores.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import retrofit2.Response

class GetCoresFragment : Fragment(), KodeinAware {

  override val kodein by kodein()

  private lateinit var coreAdapter: CoresAdapter

  private val args: GetCoresFragmentArgs by navArgs()

  private val observer = Observer<Response<List<CoreModel>>> {
    pbGetCores.gone()
    if (it.isSuccessful) {
      it.body()?.let { items -> coreAdapter.loadItems(items) }
    } else {
      srlGetCores.showSnackbar(getString(R.string.error_retrieving_data))
      findNavController().popBackStack()
    }
    rvGetCores.scheduleLayoutAnimation()
  }

  private val viewModelFactory: CoreSharedViewModelFactory by instance()

  private val viewModel: CoresSharedViewModel by lazy {
    ViewModelProviders
      .of(this, viewModelFactory)
      .get(CoresSharedViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_get_cores, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModel.apply {
      allCores.observe(this@GetCoresFragment, observer)
      upcomingCores.observe(this@GetCoresFragment, observer)
      pastCores.observe(this@GetCoresFragment, observer)
    }

    bindAdapter()
    getData()
    srlGetCores.setOnRefreshListener {
      getData()
      srlGetCores.isRefreshing = false
    }
  }

  private fun getData() {
    pbGetCores.show()
    when (args.coresToGetArgs) {
      1 -> viewModel.getAllCapsules()
      2 -> viewModel.getPastCores()
      3 -> viewModel.getUpcomingCores()
      else -> {
        pbGetCores.gone()
        srlGetCores.showSnackbar(getString(R.string.error_retrieving_data))
        findNavController().popBackStack()
      }
    }
  }

  private fun bindAdapter() {
    coreAdapter = CoresAdapter {}
    rvGetCores.apply {
      layoutManager = LinearLayoutManager(context)
      addItemDecoration(MarginItemDecorator())
      layoutAnimation = AnimationUtils
        .loadLayoutAnimation(context, R.anim.layout_animation_down_to_up)
      adapter = coreAdapter
    }
  }
}
