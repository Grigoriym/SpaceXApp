package com.grappim.spacexapp.di.components

import com.grappim.spacexapp.di.scopes.FragmentScope
import com.grappim.spacexapp.ui.capsules.GetCapsulesFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface CapsulesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CapsulesComponent
    }

    fun inject(getCapsulesFragment: GetCapsulesFragment)

}