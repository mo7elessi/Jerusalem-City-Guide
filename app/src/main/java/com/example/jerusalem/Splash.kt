package com.example.jerusalem


import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment


/**
 * A simple [Fragment] subclass.
 */
class Splash : Fragment() {
    private var TIME_OUT: Long = 3000
    val MY_PREFS_NAME = "MyPrefsFile";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler().postDelayed({

            if (getState() == true) {
                (activity as AppCompatActivity?)!!.delegate.localNightMode =
                    AppCompatDelegate.MODE_NIGHT_NO
            } else {
                (activity as AppCompatActivity?)!!.delegate.localNightMode =
                    AppCompatDelegate.MODE_NIGHT_YES
            }
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.mainContainer,
                home()
            ).commit()
        }, TIME_OUT)

        return root

    }

    //var sharedPreferences: SharedPreferences? = null

    fun getState(): Boolean {
        val prefs: SharedPreferences = activity!!.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
        return prefs.getBoolean("bkey", false)
    }

}
