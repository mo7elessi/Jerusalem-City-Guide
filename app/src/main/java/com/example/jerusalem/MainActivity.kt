package com.example.jerusalem

import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val MY_PREFS_NAME = "MyPrefsFile";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (getstate() == 1) {
            setTheme(R.style.AppTheme)

        } else if (getstate() == 2) {
            setTheme(R.style.font_vexa)
        }
        else if (getstate() == 3) {
            setTheme(R.style.forsha_font)
        }
        else if (getstate() == 4) {
            setTheme(R.style.bepo_font)
        }
        else if (getstate() == 5) {
            setTheme(R.style.somra_font)
        }


        supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            Splash()
        ).commit()

    }

    override fun setTheme(theme: Resources.Theme?) {
        super.setTheme(theme)
    }

    fun getstate(): Int {
        val prefs: SharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
        return prefs.getInt("font", 1)
    }


}
