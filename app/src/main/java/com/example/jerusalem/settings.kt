package com.example.jerusalem

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.jerusalem.settings
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*


/**
 * A simple [Fragment] subclass.
 */
class settings : Fragment() {
    val MY_PREFS_NAME = "MyPrefsFile"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        if (getfont() == 1) {
            root.radio0.isChecked
        } else if (getfont() == 2) {
            root.radio1.isChecked
        } else if (getfont() == 3) {
            root.radio2.isChecked
        } else if (getfont() == 4) {
            root.radio3.isChecked
        } else if (getfont() == 5) {
            root.radio4.isChecked

        }
        if(getstate() == true) {

            (activity as AppCompatActivity?)!!.delegate.localNightMode =
                AppCompatDelegate.MODE_NIGHT_NO
            root.switch_btn.setText("وضع النهار مفعل حاليا")
            root. switch_btn.setChecked(false);
        }else{
            (activity as AppCompatActivity?)!!.delegate.localNightMode =
                AppCompatDelegate.MODE_NIGHT_YES
            root.switch_btn.setText("وضع الليل مفعل حاليا")
            root. switch_btn.setChecked(true);


        }
            root.switch_btn.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    (activity as AppCompatActivity?)!!.delegate.localNightMode =
                        AppCompatDelegate.MODE_NIGHT_YES
                    switch_btn.setText("وضع الليل مفعل حاليا")
                    setstate(false)
                } else {
                    (activity as AppCompatActivity?)!!.delegate.localNightMode =
                        AppCompatDelegate.MODE_NIGHT_NO
                    switch_btn.setText("وضع النهار مفعل حاليا")
                    setstate(true)


            }
        }


        root.radioGroup1.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                root.radio0.id -> {
                    setfont(1)

                    root.reRun.visibility = View.VISIBLE
                }
                root.radio1.id -> {
                    setfont(2)
                    root.reRun.visibility = View.VISIBLE

                }
                root.radio2.id -> {
                    setfont(3)
                    root.reRun.visibility = View.VISIBLE

                }
                root.radio3.id -> {
                    setfont(4)
                    root.reRun.visibility = View.VISIBLE

                }
                root.radio4.id -> {
                    setfont(5)
                    root.reRun.visibility = View.VISIBLE

                }

            }
        }

        return root
    }

    fun setstate(value: Boolean) {
        val editor: SharedPreferences.Editor =
            activity!!.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit()

        editor.putBoolean("bkey", value)
        editor.apply()
    }
    fun setfont(value: Int) {
        val editor: SharedPreferences.Editor =
            activity!!.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit()
        editor.putInt("font", value)
        editor.apply()
    }
    fun getfont(): Int {
        val prefs: SharedPreferences = activity!!.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
        return prefs.getInt("font", 1)
    }

    fun getstate(): Boolean {
        val prefs: SharedPreferences = activity!!.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
        return prefs.getBoolean("bkey", false)
    }

}
