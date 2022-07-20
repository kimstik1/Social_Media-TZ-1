package com.kimstik.social_media.data.model.carousel

import com.google.gson.annotations.SerializedName

data class NetworkCarousel (
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String
)