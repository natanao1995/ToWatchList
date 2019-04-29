package com.example.towatchlist.model.remote.entity

import com.google.gson.annotations.SerializedName

data class Error401Response(
    @SerializedName("status_message")
    val statusMessage: String?,
    val success: Boolean?,
    @SerializedName("status_code")
    val statusCode: Int?
)