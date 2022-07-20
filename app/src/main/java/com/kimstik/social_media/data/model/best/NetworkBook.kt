package com.kimstik.social_media.data.model.best

import com.google.gson.annotations.SerializedName

data class NetworkBook (

    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("price") val price: Double,
    @SerializedName("image") val image: String,
    @SerializedName("rate") val rate: NetworkRate,
)