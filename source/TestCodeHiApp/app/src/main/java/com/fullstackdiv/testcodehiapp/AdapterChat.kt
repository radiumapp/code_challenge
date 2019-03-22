package com.fullstackdiv.testcodehiapp

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Created by Angga N P on 10/25/2018.
 */


class AdapterChat(private val mContext: Context, var chatModels: List<ChatDetailModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class VH0(view: View) : RecyclerView.ViewHolder(view) {
        var tvDate: TextView = view.findViewById(R.id.tvDate)
    }

    inner class VH1(view: View) : RecyclerView.ViewHolder(view) {

        // Default View
        var tvName: TextView = view.findViewById(R.id.tvName)
        var tvDate: TextView = view.findViewById(R.id.tvDate)

        // Text
        var tvChat: TextView = view.findViewById(R.id.tvChat)

        // Document Attachment
        var CLIDocument: ConstraintLayout = view.findViewById(R.id.CLIDocument)
        var ivDoc: ImageView = view.findViewById(R.id.ivDoc)
        var tvDoc: TextView = view.findViewById(R.id.tvDoc)

        // Single Contact Attachment
        var CLIContact: ConstraintLayout = view.findViewById(R.id.CLIContact)
        var ivPP: ImageView = view.findViewById(R.id.ivDoc)
        var tvContact: TextView = view.findViewById(R.id.tvContact)
        var tvMessage: TextView = view.findViewById(R.id.tvSingleImage)
        var tvAdd: TextView = view.findViewById(R.id.tvAdd)

        // List Contact Attachment
        var CLIListCt: ConstraintLayout = view.findViewById(R.id.CLIListCt)
        var ivCt: ImageView = view.findViewById(R.id.ivCt)
        var tvCt: TextView = view.findViewById(R.id.tvCt)

        // Single Image Attachment
        var CLIImage: ConstraintLayout = view.findViewById(R.id.CLIImage)
        var ivSingleImage: ImageView = view.findViewById(R.id.ivSingleImage)
        var tvSingleImage: TextView = view.findViewById(R.id.tvSingleImage)

        // List Image Attachment
        var CLIListImage: ConstraintLayout = view.findViewById(R.id.CLIListImage)
        var iv1: ImageView = view.findViewById(R.id.iv1)
        var iv2: ImageView = view.findViewById(R.id.iv2)
        var iv3: ImageView = view.findViewById(R.id.iv3)
        var iv4: ImageView = view.findViewById(R.id.iv4)
        var FLMore: FrameLayout = view.findViewById(R.id.FLMore)
        var tvMore: TextView = view.findViewById(R.id.tvMore)
    }

    inner class VH3(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemViewType(position: Int): Int {
        return when{
            chatModels[position].type!!.toLowerCase() == mContext.getString(R.string.id_merge) -> 3
            chatModels[position].from!!.toLowerCase() == "a" -> 1
            chatModels[position].from!!.toLowerCase() == "b" -> 2
            else -> 0
        }
    }

    override fun getItemCount(): Int {
        return chatModels.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view0 = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_header, parent, false)
        val view1 = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_left, parent, false)
        val view2 = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_right, parent, false)
        val view3 = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_empty, parent, false)

        return when (viewType){
            1 ->VH1(view1)
            2 ->VH1(view2)
            3 ->VH3(view3)
            else ->VH0(view0)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cm = chatModels[position]

        when {
            // Merged Type is hidden

            // Chat Type
            holder.itemViewType == 1 || holder.itemViewType == 2 -> {
                val vh = holder as VH1

                vh.tvName.text = cm.from
                if (Helper.getDate(cm.timestamp!!.toLong()) == Helper.currentDate) {
                    vh.tvDate.text = Helper.getTimeOnly(cm.timestamp!!.toLong())
                } else {
                    vh.tvDate.text = Helper.getDateMonth(cm.timestamp!!.toLong())
                }

                // Reset Visibility
                vh.CLIDocument.visibility = View.GONE
                vh.CLIContact.visibility = View.GONE
                vh.CLIImage.visibility = View.GONE
                vh.CLIContact.visibility = View.GONE
                vh.CLIListCt.visibility = View.GONE
                vh.CLIListImage.visibility = View.GONE
                vh.tvChat.visibility = View.GONE

                when{
                    cm.attachment == null ->{
                        vh.tvChat.visibility = View.VISIBLE
                        vh.tvChat.text = cm.body
                    }
                    cm.type!!.toLowerCase() == mContext.getString(R.string.id_list_image) ->{
                        if (cm.concat_count!! > 4) {
                            vh.tvMore.text = "+ ${cm.concat_count!! - 4}"
                            vh.tvMore.visibility = View.VISIBLE
                            vh.FLMore.visibility = View.VISIBLE
                        }
                        vh.CLIListImage.visibility = View.VISIBLE
                    }

                    cm.type!!.toLowerCase() == mContext.getString(R.string.id_list_contact) ->{
                        vh.tvCt.text =
                            if(cm.concat_count!! == 2) "${cm.attachment} & ${chatModels[position-1].attachment}"
                            else "${cm.attachment}, ${chatModels[position-1].attachment}" +
                                    ", and ${(cm.concat_count!! - 2)} More"

                        vh.CLIListCt.visibility = View.VISIBLE

                        vh.CLIListCt.visibility = View.VISIBLE
                    }

                    cm.attachment!!.toLowerCase() == mContext.getString(R.string.id_document) ->{
                        vh.CLIDocument.visibility = View.VISIBLE
                        vh.tvDoc.text = cm.attachment
                    }

                    cm.attachment!!.toLowerCase() == mContext.getString(R.string.id_contact) ->{
                        vh.CLIContact.visibility = View.VISIBLE
                        vh.tvContact.text = cm.attachment
                    }

                    cm.attachment!!.toLowerCase() == mContext.getString(R.string.id_image) ->{
                        vh.CLIImage.visibility = View.VISIBLE
                        if (cm.body != null){
                            vh.tvSingleImage.text = cm.body
                            vh.tvSingleImage.visibility = View.VISIBLE
                        } else vh.tvSingleImage.visibility = View.GONE
                    }
                }
            }

            // Header Type
            holder.itemViewType == 0 -> {
                val vh0 = holder as VH0
                vh0.tvDate.text = Helper.getDate(cm.timestamp!!.toLong())
            }
        }

    }
}