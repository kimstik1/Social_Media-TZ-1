package com.kimstik.social_media.data.model.similar

import com.google.gson.annotations.SerializedName

data class NetworkSimilar (

    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
)