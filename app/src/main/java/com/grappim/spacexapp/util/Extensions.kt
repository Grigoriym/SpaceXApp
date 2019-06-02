package com.grappim.spacexapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.grappim.spacexapp.R
import com.grappim.spacexapp.recyclerview.MarginItemDecorator
import kotlinx.coroutines.*
import retrofit2.Response
import timber.log.Timber

inline val Context.connectivityManager: ConnectivityManager?
  get() = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

fun View.showKeyboard() {
  val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  this.requestFocus()
  imm.showSoftInput(this, 0)
}

fun View.hideKeyboard(): Boolean {
  return try {
    val inputMethodManager =
      context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
  } catch (ignored: RuntimeException) {
    false
  }
}

inline fun <reified T : Activity> Context?.startActivity() =
  this?.startActivity(Intent(this, T::class.java))

inline fun <reified T : Any> Activity.launchActivity(
  requestCode: Int = -1,
  options: Bundle? = null,
  noinline init: Intent.() -> Unit = {}
) {
  val intent = newIntent<T>(this)
  intent.init()
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    startActivityForResult(intent, requestCode, options)
  } else {
    startActivityForResult(intent, requestCode)
  }
}

inline fun <reified T : Any> Context.launchActivity(
  options: Bundle? = null,
  noinline init: Intent.() -> Unit = {}
) {
  val intent = newIntent<T>(this)
  intent.init()
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    startActivity(intent, options)
  } else {
    startActivity(intent)
  }
}

fun Fragment.startBrowser(uriString: String?) {
  val i = Intent(Intent.ACTION_VIEW)
  i.data = Uri.parse(uriString)
  activity?.startActivity(i)
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
  Intent(context, T::class.java)

fun Context.inflateLayout(
  @LayoutRes layoutId: Int, parent: ViewGroup? = null,
  attachToRoot: Boolean = false
): View =
  LayoutInflater.from(this).inflate(layoutId, parent, attachToRoot)

fun View.gone(): View {
  if (visibility != View.GONE) {
    visibility = View.GONE
  }
  return this
}

fun View.hide(): View {
  if (visibility != View.INVISIBLE) {
    visibility = View.INVISIBLE
  }
  return this
}

fun View.show(): View {
  if (visibility != View.VISIBLE) {
    visibility = View.VISIBLE
  }
  return this
}

inline fun View.showIf(condition: () -> Boolean): View {
  visibility = if (visibility != View.VISIBLE && condition()) {
    View.VISIBLE
  } else {
    View.GONE
  }
  return this
}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
  itemView.setOnClickListener {
    event.invoke(adapterPosition, itemViewType)
  }
  return this
}

fun View.showSnackbar(text: String, timeLength: Int = Snackbar.LENGTH_LONG) {
  Snackbar.make(this, text, timeLength).show()
}

fun <T> fetchData(req: Deferred<Response<T>>, mld: MutableLiveData<T>) {
  Timber.d("Extensions - fetchData")
  CoroutineScope(Dispatchers.IO).launch {
    val response = req.await()
    if (response.isSuccessful) {
      Timber.d("Extensions - fetchData - response is successful")
      withContext(Dispatchers.Main) {
        response.body()?.let { mld.value = it }
      }
    }
  }
}

fun <T> fetchNetworkData(req: Deferred<Response<T>>, mld: MutableLiveData<Response<T>>) {
  Timber.d("Extensions - fetchData")
  CoroutineScope(Dispatchers.IO).launch {
    val response = req.await()
    withContext(Dispatchers.Main) {
      mld.value = response
    }
  }
}

fun setMyImageResource(bool: Boolean?): Int =
  when (bool) {
    true -> R.drawable.ic_check_circle_black_24dp
    else -> R.drawable.ic_cancel_black_24dp
  }