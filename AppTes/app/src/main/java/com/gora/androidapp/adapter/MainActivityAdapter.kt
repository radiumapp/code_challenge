package com.gora.androidapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gora.androidapp.R
import com.gora.androidapp.model.messageFilterResponse
import kotlinx.android.synthetic.main.item_list.view.*

class MainActivityAdapter(
        private val context: Context,
        private val items: MutableList<messageFilterResponse>
): RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], context)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bindItem(items: messageFilterResponse, context: Context){

            when(items.type){
                "-" -> { //pesan teks
                    itemView.tvMessage.text     = items.message
                    itemView.tvMessage.setTextColor(context.resources.getColor(R.color.siam))
                    itemView.tvImage.visibility = View.GONE
                    itemView.gcImage.visibility = View.GONE
                    itemView.tvMessage.visibility = View.VISIBLE
                }
                "image" -> { //pesan_image

                    if(items.message.equals("-")){
                        //image with no message
                        if((items.totalCollection)!!.toInt()>=3){
                            itemView.gcImage.visibility = View.VISIBLE
                            itemView.tvImage.visibility = View.GONE
                        }else{
                            itemView.gcImage.visibility = View.GONE
                            itemView.tvImage.visibility = View.VISIBLE
                            itemView.tvImage.setImageResource(context.resources.getIdentifier(items.lampiran,"drawable", "com.gora.androidapp"))
                        }
                        itemView.tvMessage.visibility = View.GONE

                    }else{
                        //image with message
                        itemView.tvMessage.text = items.message
                        itemView.tvMessage.setTextColor(context.resources.getColor(R.color.siam))
                        itemView.tvImage.setImageResource(context.resources.getIdentifier(items.lampiran,"drawable", "com.gora.androidapp"))

                        itemView.tvMessage.visibility = View.VISIBLE
                        itemView.gcImage.visibility = View.GONE
                        itemView.tvImage.visibility = View.VISIBLE

                    }
                }
                "contact" -> {//pesan_contact

                    if(items.totalCollection!!.toInt()>=1){
                        itemView.tvMessage.text = items.type +" and +"+ items.totalCollection!!.toInt() + " Other contact"
                    }else{
                        itemView.tvMessage.text = items.type
                    }

                    itemView.tvMessage.setTextColor(context.resources.getColor(R.color.colorAccent))
                    itemView.gcImage.visibility = View.GONE
                    itemView.tvImage.visibility = View.GONE
                    itemView.tvMessage.visibility = View.VISIBLE

                }
                "document" -> {//pesan_document

                    itemView.tvMessage.visibility = View.GONE
                    itemView.gcImage.visibility = View.GONE
                    itemView.tvImage.visibility = View.VISIBLE
                    itemView.tvImage.setImageResource(context.resources.getIdentifier(items.lampiran,"drawable", "com.gora.androidapp"))
                }

            }


            itemView.tvDateTime.text    = items.dateTime
            if(items.position.equals("R")){
                itemView.rlParent.gravity = Gravity.RIGHT
                itemView.rlContainer.setBackgroundResource(R.drawable.background_right)
            }else{
                itemView.rlParent.gravity = Gravity.LEFT
                itemView.rlContainer.setBackgroundResource(R.drawable.background_left)
            }
        }
    }
}