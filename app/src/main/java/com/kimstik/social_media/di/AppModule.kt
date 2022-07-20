package com.kimstik.social_media.di

import android.content.Context
import com.kimstik.social_media.data.repository.NetworkRepository
import com.kimstik.social_media.util.factory.MainViewModelFactory
import com.kimstik.social_media.util.factory.ScopeViewModelFactory
import com.kimstik.social_media.util.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context.applicationContext

    @Provides
    fun provideAddCardViewModelFactory(repos: NetworkRepository, dispatchers: DispatcherProvider): MainViewModelFactory =
        MainViewModelFactory(repos = repos, dispatchers = dispatchers)

    @Provides
    fun provideReviewViewModelFactory(repos: NetworkRepository, dispatchers: DispatcherProvider): ScopeViewModelFactory =
        ScopeViewModelFactory(repos = repos, dispatchers = dispatchers)
}