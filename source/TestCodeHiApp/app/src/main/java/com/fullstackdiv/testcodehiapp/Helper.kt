package com.fullstackdiv.testcodehiapp

import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * Created by Angga N P on 10/26/2018.
 */

class Helper {

    companion object {
        val currentUnix: Long
            get() = System.currentTimeMillis()

        val currentDate: String
            get() {
                val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val date = Date(currentUnix)
                return sdf.format(date).toString()
            }

        fun getDate(unix: Long): String {
            val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = Date(unix * 1000)
            return sdf.format(date).toString()
        }

        fun getDateMonth(unix: Long): String {
            val sdf = java.text.SimpleDateFormat("MMM dd", Locale.getDefault())
            val date = Date(unix * 1000)
            return sdf.format(date).toString()
        }

        fun getTimeOnly(unix: Long): String {
            val sdf = java.text.SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = Date(unix * 1000)
            
            return sdf.format(date)
        }

    }

}