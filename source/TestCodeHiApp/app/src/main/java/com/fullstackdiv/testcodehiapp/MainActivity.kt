package com.fullstackdiv.testcodehiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    private var cm : ChatModel = ChatModel()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = title
        setSupportActionBar(toolbar)

        extractData()
    }

    override fun onDestroy() {
        super.onDestroy()

        // don't send events once the activity is destroyed
        compositeDisposable.clear()
    }

    fun extractData(){
        val myJson = inputStreamToString(this.resources.openRawResource(R.raw.message_dataset))
        cm = Gson().fromJson(myJson, ChatModel::class.java)
        cm.data = ArrayList(cm.data!!.sortedWith(compareBy { it.id }))
        custommizeData()
    }

    fun custommizeData(){
        compositeDisposable.add(
            Observable.fromIterable(cm.data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ChatDetailModel>() {
                    var count = 0
                    var merge = false

                    override fun onNext(t: ChatDetailModel) {
                        val position = cm.data!!.indexOf(t)

                        val iBefore = if (position > 0) cm.data!![position-1] else null

                        val iAfter = if (position != cm.data!!.size-1) cm.data!![position+1] else null

                        if(iAfter != null) {
                            val attach = t.attachment != null && t.body == null && t.attachment != getString(R.string.id_document)
                            val sameSender = iAfter.from == t.from
                            val sameType = iAfter.attachment == t.attachment
                            val sameDay =  Helper.getDate(iAfter.timestamp!!.toLong()) == Helper.getDate(t.timestamp!!.toLong())

                            if (!sameDay) merge = true

                            if(attach){
                                when{
                                    // Fresh Start Counter
                                    count == 0 -> count += 1

                                    // Type Change Flag to Merge
                                    !sameSender || !sameType ->{
                                        if (iBefore!!.from == t.from && iBefore.attachment == t.attachment) count += 1
                                        merge = true
                                    }

                                    // Same type and Sender
                                    sameSender && sameType -> count += 1
                                }
                            } else merge = true
                        }

                        // Manipulate Data Model
                        when {
                            count >1 && !t.attachment.isNullOrBlank() && t.attachment == getString(R.string.id_contact) -> {
                                iBefore!!.show = false
                                iBefore.type = getString(R.string.id_merge)
                            }
                            count == 4 && !t.attachment.isNullOrBlank() && t.attachment == getString(R.string.id_image) -> {
                                for (x in position-4 until position) {
                                    cm.data!![x].show = false
                                    cm.data!![x].type = getString(R.string.id_merge)
                                }
                            }
                            count > 4 && !t.attachment.isNullOrBlank() && t.attachment == getString(R.string.id_image) -> {
                                iBefore!!.show = false
                                iBefore.type = getString(R.string.id_merge)
                            }
                        }

                        // Merge the data
                        if(merge || position == cm.data!!.size-1){
                            if (!t.attachment.isNullOrBlank() && t.attachment == "contact" && count > 1) {
                                t.type = getString(R.string.id_list_contact)
                                t.concat_count = count
                            } else if (!t.attachment.isNullOrBlank() && t.attachment == "image" && count > 3) {
                                t.type = getString(R.string.id_list_image)
                                t.concat_count = count
                            }

                            // Reset Counter
                            count = 0
                            merge = false
                        }
                    }

                    override fun onError(e: Throwable) {
                       e.printStackTrace()
                    }

                    override fun onComplete() {
                        println("All items are emitted!")
                        addHeader()
                    }
                })
        )
    }

    fun addHeader(){
        compositeDisposable.add(
            Observable.fromIterable(cm.data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ChatDetailModel>() {
                    override fun onNext(t: ChatDetailModel) {
                        val position = cm.data!!.indexOf(t)
                        val iAfter = if (position != cm.data!!.size-1) cm.data!![position+1] else null

                        // First Position add Header
                        if (position == 0) cm.data!!.add(position, ChatDetailModel(iAfter!!.timestamp))

                        if(iAfter != null) {
                            val sameDay =  Helper.getDate(iAfter.timestamp!!.toLong()) == Helper.getDate(t.timestamp!!.toLong())
                            if (!sameDay) cm.data!!.add(position+1, ChatDetailModel(iAfter.timestamp))
                        }
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                    override fun onComplete() {
                        setBaseView()
                    }
                })
        )
    }

    fun setBaseView(){
        val adapterChat = AdapterChat(this, cm.data!!)
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.isSmoothScrollbarEnabled = true

        rv.layoutManager = mLayoutManager
        rv.itemAnimator = DefaultItemAnimator()
        rv.adapter = adapterChat
    }


    fun inputStreamToString(inputStream: InputStream):String? {
        return try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            String(bytes)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
