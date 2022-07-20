package com.kimstik.social_media.data.repository

import com.kimstik.social_media.data.datasource.Api
import com.kimstik.social_media.data.model.best.NetworkBook
import com.kimstik.social_media.data.model.carousel.NetworkCarousel
import com.kimstik.social_media.data.model.similar.NetworkSimilar
import retrofit2.Response

class NetworkRepositoryImpl(private val api: Api): NetworkRepository {

    override suspend fun getBestBooks(): List<NetworkBook>? = api.getBest().execute().body()

    override suspend fun getCarousel(): List<NetworkCarousel>? = api.getCarousel().execute().body()

    override suspend fun getSimilar(): List<NetworkSimilar>? = api.getSimilar().execute().body()
}