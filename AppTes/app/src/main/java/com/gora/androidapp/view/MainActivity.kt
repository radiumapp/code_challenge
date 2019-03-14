package com.gora.androidapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.gora.androidapp.R
import com.gora.androidapp.adapter.MainActivityAdapter
import com.gora.androidapp.config.toyBox
import com.gora.androidapp.model.messageDataResponse
import com.gora.androidapp.model.messageFilterResponse
import com.gora.androidapp.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var presenter: MainActivityPresenter
    private lateinit var adapter: MainActivityAdapter
    private var items: MutableList<messageFilterResponse> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenter(this)
        presenter.getData(this)

        adapter = MainActivityAdapter(this, items)
        rvMessage.layoutManager = LinearLayoutManager(this)
        rvMessage.adapter = adapter
    }

    override fun showMessage(data: List<messageDataResponse>) {

        var me          = "A"
        lateinit var message: String
        lateinit var type: String
        lateinit var dateTime: String
        lateinit var position: String
        lateinit var lampiran: String

        /*for  (item in data){
            Log.d("log",item.id)
            Log.d("log", toyBox.unixTimeToDate(item.timestamp))
            Log.d("log","--------------------------------------")
        }*/

        //sort ascending by timestamp
        toyBox.sortAscByTimestamp(data)

        items.clear()
        for  (item in data){

            message = item.body?:"-"
            type    = item.attachment?:"-"
            dateTime= toyBox.unixTimeToDate(item.timestamp)?:"-"
            if(item.from.equals(me)){
                position = "R"
            }else{
                position = "L"
            }
            if(type.equals("image")){
                //lampiran = "R.drawable.image"+item.id
                lampiran = "image"+item.id
            }else{
                lampiran = "-"
            }

            items.add(messageFilterResponse(message=message,type=type,dateTime=dateTime,position=position,lampiran=lampiran))
        }

        adapter.notifyDataSetChanged()
    }
}
