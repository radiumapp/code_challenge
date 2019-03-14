package com.gora.androidapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.gora.androidapp.R
import com.gora.androidapp.adapter.MainActivityAdapter
import com.gora.androidapp.config.toyBox
import com.gora.androidapp.model.messageDataResponse
import com.gora.androidapp.model.messageFilterResponse
import com.gora.androidapp.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*

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

        lateinit var message: String
        lateinit var type: String
        lateinit var dateTime: String
        lateinit var position: String
        lateinit var lampiran: String

        toyBox.sortAscByTimestamp(data)

        items.clear()

        var i = 0
        loopAwal@ while (i <data.count()) {

            message = data[i].body?:"-"
            type    = data[i].attachment?:"-"
            dateTime= toyBox.unixTimeToDate(data[i].timestamp)?:"-"

            when(data[i].from){
                "A" -> {
                    position = "R"
                }
                else -> {
                    position = "L"
                }
            }

            when(type){
                "image" -> {
                    lampiran = "image"+data[i].id
                }
                "document" -> {
                    lampiran = "ic_baseline_insert_drive_file"
                }
                else -> {
                    lampiran = "-"
                }
            }

            var typeNow    = data[i].attachment?:"-"
            var dateNow     = toyBox.unixTimeToDate(data[i].timestamp)
            var fromNow     = data[i].from?:"-"
            var messageNow  = data[i].body?:"-"

            var totalCollection = 0
            var n=i+1

            when(type){
                "image" -> {
                    loopCollection@ while(n<data.count()){

                        var typeNext    = data[n].attachment?:"-"
                        var dateNext    = toyBox.unixTimeToDate(data[n].timestamp)
                        var fromNext    = data[n].from?:"-"
                        var messageNext = data[n].body?:"-"

                        if((typeNow==typeNext)&& (dateNow==dateNext)&& (fromNow==fromNext)&& (messageNow=="-")&& (messageNext=="-")){
                            totalCollection++
                        }else{

                            break@loopCollection
                        }

                        n++

                    }

                }
                "contact" -> {
                    loopCollectionContact@ while(n<data.count()){

                        var typeNext    = data[n].attachment?:"-"
                        var dateNext    = toyBox.unixTimeToDate(data[n].timestamp)
                        var fromNext    = data[n].from?:"-"

                        if((typeNow==typeNext)&& (dateNow==dateNext)&& (fromNow==fromNext)){
                            totalCollection++
                        }else{
                            break@loopCollectionContact
                        }

                        n++

                    }
                }
            }

            items.add(messageFilterResponse(message=message,type=type,dateTime=dateTime,position=position,lampiran=lampiran,totalCollection=totalCollection.toString()))

            if(type.equals("image")&&totalCollection>=3){
                i=i+(totalCollection+1) //+1 karena gambar pertama tidak dihitung
            }else if(type.equals("contact")&&totalCollection>=1){
                i=i+(totalCollection+1) //+1 karena contact pertama tidak dihitung
            }else{
                i++
            }


        }
        adapter.notifyDataSetChanged()

    }
}
