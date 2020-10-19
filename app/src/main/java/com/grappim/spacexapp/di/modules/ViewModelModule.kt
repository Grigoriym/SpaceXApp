package com.grappim.spacexapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.core.platform.ViewModelFactory
import com.grappim.spacexapp.di.keys.ViewModelKey
import com.grappim.spacexapp.ui.capsules.CapsulesViewModel
import com.grappim.spacexapp.ui.cores.CoresViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CapsulesViewModel::class)
    abstract fun bindRepoViewModel(viewModel: CapsulesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoresViewModel::class)
    abstract fun bindRandomUsersViewModel(viewModel: CapsulesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}