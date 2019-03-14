package com.gora.androidapp.model

import com.google.gson.annotations.SerializedName

data class messageFilterResponse(

        @SerializedName("message")
        val message: String? = null,

        @SerializedName("type")
        val type: String? = null,

        @SerializedName("dateTime")
        val dateTime: String? = null,

        @SerializedName("position")
        val position: String? = null,

        @SerializedName("lampiran")
        val lampiran: String? = null,

        @SerializedName("totalCollection")
        val totalCollection: String? = null
)