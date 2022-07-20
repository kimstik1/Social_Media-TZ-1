package com.kimstik.social_media.data.repository

import com.kimstik.social_media.data.model.best.NetworkBook
import com.kimstik.social_media.data.model.carousel.NetworkCarousel
import com.kimstik.social_media.data.model.similar.NetworkSimilar
import retrofit2.Response

interface NetworkRepository {
    suspend fun getBestBooks(): List<NetworkBook>?
    suspend fun getSimilar(): List<NetworkSimilar>?
    suspend fun getCarousel(): List<NetworkCarousel>?
}