package com.grappim.spacexapp.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.grappim.spacexapp.di.scopes.GsonScope
import dagger.Module
import dagger.Provides

@Module
object GsonModule {

    @GsonScope
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

}