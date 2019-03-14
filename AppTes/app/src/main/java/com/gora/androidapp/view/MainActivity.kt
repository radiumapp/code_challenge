package com.gora.androidapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import com.google.gson.Gson
import com.gora.androidapp.config.toyBox
import com.gora.androidapp.model.messageResponse
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*try {
            val inputStream: InputStream = assets.open("message_dataset.json")
            val inputStreamReader = InputStreamReader(inputStream)
            val sb = StringBuilder()
            var line: String?
            val br = BufferedReader(inputStreamReader)
            line = br.readLine()
            while (br.readLine() != null) {
                sb.append(line)
                line = br.readLine()
            }
            br.close()
            Log.d("tes",sb.toString())
        } catch (e:Exception){
            Log.d("tes", e.toString())
        }*/


        var gson = Gson()

        try {
            val inputStream:InputStream = assets.open("message_dataset.json")
            val inputString = inputStream.bufferedReader().use{it.readText()}
            var mData = gson?.fromJson(inputString,messageResponse::class.java)

            for  (item in mData.data){
                Log.d("try",item.id)
                Log.d("try",toyBox.unixTimeToDate(item.timestamp))
                Log.d("try","--------------------------------------")
            }
        } catch (e:Exception){
            Log.d("catch", e.toString())
        }
    }
}
