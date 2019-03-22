package com.fullstackdiv.testcodehiapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ChatModel {
    @SerializedName("data")
    @Expose
    var data: MutableList<ChatDetailModel>? = arrayListOf()
}