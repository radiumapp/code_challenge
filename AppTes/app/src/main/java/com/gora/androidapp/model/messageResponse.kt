package com.gora.androidapp.model

import com.google.gson.annotations.SerializedName

data class messageResponse(
    @SerializedName("data")
    val data: List<messageDataResponse>
)