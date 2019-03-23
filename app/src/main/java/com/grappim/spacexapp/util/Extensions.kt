package com.grappim.spacexapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

fun Boolean.asInt(): Int {
  return if (this) 1 else 0
}

fun Int.asBoolean(): Boolean {
  return (this == 1)
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

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
  itemView.setOnClickListener {
    event.invoke(adapterPosition, itemViewType)
  }
  return this
}

fun <T> Call<T>.enqueue(callback: CallbackKt<T>.() -> Unit) {
  val callbackKt = CallbackKt<T>()
  callback.invoke(callbackKt)
  this.enqueue(callbackKt)
}

class CallbackKt<T> : Callback<T> {
  var onResponse: ((Response<T>) -> Unit)? = null
  var onFailure: ((t: Throwable?) -> Unit)? = null

  override fun onFailure(call: Call<T>, t: Throwable) {
    onFailure?.invoke(t)
  }

  override fun onResponse(call: Call<T>, response: Response<T>) {
    onResponse?.invoke(response)
  }
}

fun View.showSnackbar(text: String, timeLength: Int) {
  Snackbar.make(this, text, timeLength).show()
}

inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
  beginTransaction()
    .apply {
      action()
    }.commit()
}

fun <T> fetchData(req: Deferred<Response<T>>, mld: MutableLiveData<T>) {
  CoroutineScope(Dispatchers.Main).launch {
    val response = req.await()
    if (response.isSuccessful) {
      response.body()?.let { mld.value = it }
    }
  }
}