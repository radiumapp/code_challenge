package com.gora.androidapp.model

import com.google.gson.annotations.SerializedName

data class messageDataResponse(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("body")
    val body: String? = null,

    @SerializedName("attachment")
    val attachment: String? = null,

    @SerializedName("timestamp")
    val timestamp: String? = null,

    @SerializedName("from")
    val from: String? = null,

    @SerializedName("to")
    val to: String? = null
)