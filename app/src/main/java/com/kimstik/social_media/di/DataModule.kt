package com.kimstik.social_media.di

import com.kimstik.social_media.data.datasource.Api
import com.kimstik.social_media.data.repository.NetworkRepository
import com.kimstik.social_media.data.repository.NetworkRepositoryImpl
import com.kimstik.social_media.util.dispatcher.DefaultDispatcherProvider
import com.kimstik.social_media.util.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideNetworkRepository(api: Api): NetworkRepository = NetworkRepositoryImpl(api = api)

    @Provides
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()
}