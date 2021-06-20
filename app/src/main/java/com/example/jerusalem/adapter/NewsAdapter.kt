package com.example.jerusalem.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jerusalem.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(
    var activity: Activity,
    var data: MutableList<com.example.jerusalem.model.NewsModel>,
    val click: onClick
) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvtitle = itemView.titleNews
        val ttvdesc = itemView.descNews
        val tvimage = itemView.imageNews
        val cardNews = itemView.cardNews


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.news_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvtitle.text = data[position].mtitleNews
        holder.ttvdesc.text = data[position].mdescNews
        Picasso.get().load(data[position].mimageNews).into(holder.tvimage)
        holder.cardNews.setOnClickListener {
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