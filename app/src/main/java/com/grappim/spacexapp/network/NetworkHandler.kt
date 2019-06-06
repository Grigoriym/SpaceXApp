package com.grappim.spacexapp.network

import android.content.Context
import com.grappim.spacexapp.util.networkInfo

class NetworkHandler(private val context: Context) {
  val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}