package com.grappim.spacexapp.di.components

import com.grappim.spacexapp.di.scopes.CoresScope
import com.grappim.spacexapp.ui.cores.GetCoresFragment
import dagger.Subcomponent

@CoresScope
@Subcomponent
interface CoresComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CoresComponent
    }

    fun inject(getCoresFragment: GetCoresFragment)

}