package com.example.towatchlist.model.remote.entity

import com.google.gson.annotations.SerializedName

data class Error404Response(
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("status_code")
    val statusCode: Int?
)