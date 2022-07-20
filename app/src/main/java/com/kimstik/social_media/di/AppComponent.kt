package com.kimstik.social_media.di

import com.kimstik.social_media.ui.fragments.MainFragment
import com.kimstik.social_media.ui.fragments.ScopeFragment
import dagger.Component

@JvmDefaultWithoutCompatibility
@Component(modules = [AppModule::class, DataModule::class, NetworkModule::class])
interface AppComponent {
    fun injectMainViewModel(mainFragment: MainFragment)
    fun injectScopeViewModel(scopeFragment: ScopeFragment)
}