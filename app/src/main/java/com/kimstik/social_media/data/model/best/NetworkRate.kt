package com.kimstik.social_media.data.model.best

import com.google.gson.annotations.SerializedName

data class NetworkRate (
    @SerializedName("score") val score: Double,
    @SerializedName("amount") val amount: Int
)
