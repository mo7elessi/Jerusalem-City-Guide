package com.example.jerusalem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_articles_details.view.*

/**
 * A simple [Fragment] subclass.
 */
class InfoDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=  inflater.inflate(R.layout.fragment_articles_details, container, false)
        val i = arguments
        val title = i!!.getString("title")
        val description = i.getString("description")
        val time = i.getString("time")

        root.titleInfoDetails.text = "$title"
        root.descInfoDetails.text = "$description"
        root.timeInfoDetails.text = "$time"

        return root
    }

}
