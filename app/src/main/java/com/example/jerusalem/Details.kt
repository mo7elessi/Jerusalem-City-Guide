package com.example.jerusalem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_news_detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class Details : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news_detail, container, false)
        val i = arguments
        val title = i!!.getString("title")
        val description = i.getString("description")
        val time = i.getString("time")
        val image = i.getString("image")
        time!!.replace("T","/",true).replace("Z","PM",true)

        Picasso.get().load(image).into(root.imageDetails)
        root.titleDetails.text = "$title"
        root.descDetails.text = "$description"
        root.timeDetails.text = "$time"
        return root
    }


}
