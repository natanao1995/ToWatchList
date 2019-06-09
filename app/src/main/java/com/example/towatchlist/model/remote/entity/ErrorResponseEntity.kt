package com.example.towatchlist.model.remote.entity

import com.google.gson.annotations.SerializedName

data class ErrorResponseEntity(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String
)