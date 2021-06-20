package com.example.jerusalem.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jerusalem.R
import com.example.jerusalem.model.InfoModel
import kotlinx.android.synthetic.main.item_info.view.*

class InformationAdapter(
    var activity: Activity,
    var data: MutableList<InfoModel>,
    val click: onClick
) :
    RecyclerView.Adapter<InformationAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitleinfo = itemView.titleInfo
        val tvDescinfo = itemView.descInfo
        val card = itemView.cardInfo


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.item_info, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitleinfo.text = data[position].title
        holder.tvDescinfo.text = data[position].description
        holder.card.setOnClickListener {
            click.onClickItem(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return data.size

    }

    interface onClick {
        fun onClickItem(position: Int) {


        }
    }

}