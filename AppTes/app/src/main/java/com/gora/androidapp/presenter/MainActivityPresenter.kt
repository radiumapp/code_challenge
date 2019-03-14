package com.gora.androidapp.presenter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.gora.androidapp.config.toyBox
import com.gora.androidapp.model.messageResponse
import com.gora.androidapp.view.MainActivityView
import java.io.InputStream

class MainActivityPresenter(val view: MainActivityView) {

    fun getData(context: Context){
        var gson = Gson()

        try {
            val inputStream: InputStream = context.assets.open("message_dataset.json")
            val inputString = inputStream.bufferedReader().use{it.readText()}
            var mData = gson?.fromJson(inputString, messageResponse::class.java)
            view.showMessage(mData.data)

        } catch (e:Exception){
            Log.d("catch", e.toString())
        }
    }
}