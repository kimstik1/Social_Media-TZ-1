package com.kimstik.social_media.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kimstik.social_media.data.datasource.Api
import com.kimstik.social_media.util.CustomCallAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://my-json-server.typicode.com/stellardiver/ebookdata/")
                .client(OkHttpClient())
                .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun providesApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}