package com.gora.androidapp.config

import com.gora.androidapp.model.messageDataResponse
import java.text.SimpleDateFormat
import java.util.*

object toyBox {

    fun unixTimeToDate(s: String?): String? {
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            val netDate = Date(s!!.toLong() * 1000L)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    fun sortAscByTimestamp(view: List<messageDataResponse>){
        Collections.sort(view, object : Comparator<messageDataResponse> {

            override fun compare(o1: messageDataResponse, o2: messageDataResponse): Int {
                return o1.timestamp!!.compareTo(o2.timestamp!!)
            }

        })
    }
}