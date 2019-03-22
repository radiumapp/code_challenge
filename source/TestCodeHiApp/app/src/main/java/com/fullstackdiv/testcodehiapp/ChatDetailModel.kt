package com.fullstackdiv.testcodehiapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class ChatDetailModel(t:String? = null){
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("body")
    @Expose
    var body: String? = null
    @SerializedName("attachment")
    @Expose
    var attachment: String? = ""
    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = t
    @SerializedName("from")
    @Expose
    var from: String? = ""
    @SerializedName("to")
    @Expose
    var to: String? = ""

    //Display related
    var show: Boolean? = true
    var type: String? = ""
    var concat_count: Int? = 0

}