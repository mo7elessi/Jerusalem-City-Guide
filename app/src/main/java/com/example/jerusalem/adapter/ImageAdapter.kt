package com.example.jerusalem.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jerusalem.R
import com.example.jerusalem.model.ImageModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*

class ImageAdapter(
    var activity: Activity, var data: MutableList<ImageModel>, val click: ImageAdapter.onClick
) :
    RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timage = itemView.titlePlace
        val dimage = itemView.descPlace
        val image = itemView.imagePlace
        val card = itemView.cardImage

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.item_image, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.timage.text = data[position].titleImage
        holder.dimage.text = data[position].descImage
        Picasso.get().load(data[position].imageV).into(holder.image)
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


