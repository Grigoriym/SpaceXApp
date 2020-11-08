package com.grappim.spacexapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.core.platform.ViewModelFactory
import com.grappim.spacexapp.di.keys.ViewModelKey
import com.grappim.spacexapp.ui.capsules.CapsulesViewModel
import com.grappim.spacexapp.ui.cores.CoresViewModel
import com.grappim.spacexapp.ui.history.HistoryViewModel
import com.grappim.spacexapp.ui.launches.completed.CompletedLaunchesViewModel
import com.grappim.spacexapp.ui.launches.upcoming.UpcomingLaunchesViewModel
import com.grappim.spacexapp.ui.launchpads.LaunchPadViewModel
import com.grappim.spacexapp.ui.missions_payloads.MissionViewModel
import com.grappim.spacexapp.ui.rockets.RocketsViewModel
import com.grappim.spacexapp.ui.ships.ShipsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CapsulesViewModel::class)
    abstract fun bindCapsulesViewModel(viewModel: CapsulesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoresViewModel::class)
    abstract fun bindCoresViewModel(viewModel: CoresViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RocketsViewModel::class)
    abstract fun bindRocketsViewModel(rocketsViewModel: RocketsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShipsViewModel::class)
    abstract fun bindShipsViewModel(shipsViewModel: ShipsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(historyViewModel: HistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LaunchPadViewModel::class)
    abstract fun bindLaunchPadViewModel(launchPadViewModel: LaunchPadViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompletedLaunchesViewModel::class)
    abstract fun bindCompletedLaunchesViewModel(completedLaunchesViewModel: CompletedLaunchesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MissionViewModel::class)
    abstract fun bindMissionViewModel(missionViewModel: MissionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingLaunchesViewModel::class)
    abstract fun bindUpcomingLaunchesViewModel(upcomingLaunchesViewModel: UpcomingLaunchesViewModel): ViewModel
}