package com.example.jerusalem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.jerusalem.admin.AddInfo
import com.example.jerusalem.admin.AddImage
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


/**
 * A simple [Fragment] subclass.
 */
class home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        root.btnAdd.setOnClickListener {
            PopupMenu()
        }

        root.showInfo.setOnClickListener {
            GoToFragment(Informations())
        }
        root.showImages.setOnClickListener {
            GoToFragment(JerusalemImage())
        }
        root.showNews.setOnClickListener {
            GoToFragment(News())
        }
        root.showVideos.setOnClickListener {
            GoToFragment(showVideos())
        }
        root.settings.setOnClickListener {
            GoToFragment(settings())
        }
        return root
    }


fun PopupMenu(){
    val popup = PopupMenu(activity,btnAdd)
    popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
    popup.setOnMenuItemClickListener { item ->
        when(item.itemId) {
            R.id.addInfo ->
                GoToFragment(AddInfo())

            R.id.addImg ->
                GoToFragment(AddImage())
        }

        true }
    popup.show();
}

    fun GoToFragment(fragment: Fragment) {
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            fragment
        ).addToBackStack("null").commit()

    }
}
