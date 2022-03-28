package com.grappim.spacexapp.di.components

import android.content.Context
import com.grappim.spacexapp.di.modules.*
import com.grappim.spacexapp.di.scopes.AppScope
import com.grappim.spacexapp.di.scopes.GsonScope
import com.grappim.spacexapp.ui.MainActivity
import com.grappim.spacexapp.ui.SplashActivity
import com.grappim.spacexapp.ui.capsules.CapsulesViewModel
import com.grappim.spacexapp.ui.cores.CoresViewModel
import dagger.BindsInstance
import dagger.Component

@AppScope
@GsonScope
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        GsonModule::class,
        AppSubComponents::class,
        AssistedInjectionModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent

    }

    fun fragmentsComponent(): FragmentsComponent.Factory

    fun capsulesViewModelFactory(): CapsulesViewModel.Factory
    fun coresViewModelFactory(): CoresViewModel.Factory

    fun inject(mainActivity: MainActivity)
    fun inject(splashActivity: SplashActivity)
}