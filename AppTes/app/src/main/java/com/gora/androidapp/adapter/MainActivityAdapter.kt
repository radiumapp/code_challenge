package com.gora.androidapp.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gora.androidapp.R
import com.gora.androidapp.config.toyBox
import com.gora.androidapp.model.messageDataResponse
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

            //pengecekan image
            if(items.type.equals("image")){
                itemView.tvImage.visibility = View.VISIBLE
                val img = context.resources.getIdentifier(items.lampiran,"drawable", "com.gora.androidapp")
                itemView.tvImage.setImageResource(img)
            }else{
                itemView.tvImage.visibility = View.GONE
            }

            //pengecekan type
            if(items.type.equals("-")&&!items.message.equals("-")){
                itemView.tvMessage.text     = items.message
            }else{
                itemView.tvMessage.text     = items.type
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