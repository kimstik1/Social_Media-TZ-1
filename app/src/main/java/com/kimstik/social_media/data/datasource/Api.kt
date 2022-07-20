package com.kimstik.social_media.data.datasource

import com.kimstik.social_media.data.model.best.NetworkBook
import com.kimstik.social_media.data.model.carousel.NetworkCarousel
import com.kimstik.social_media.data.model.similar.NetworkSimilar
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("best")
    fun getBest(): Call<List<NetworkBook>>

    @GET("carousel")
    fun getCarousel(): Call<List<NetworkCarousel>>

    @GET("similar")
    fun getSimilar(): Call<List<NetworkSimilar>>
}