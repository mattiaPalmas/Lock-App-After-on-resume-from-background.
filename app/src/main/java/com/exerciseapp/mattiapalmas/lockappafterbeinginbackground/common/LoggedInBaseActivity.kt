package com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.screens.UnlockWithPasswordActivity

abstract class LoggedInBaseActivity : AppCompatActivity() {

    private val sharedPrefs: SharedPrefs by lazy { Injection().provideSharePreference(this) }

    override fun onStart() {
        super.onStart()

        if (sharedPrefs.appIsComingFromBackground) {
            startUnlockWithPasswordActivity()
        }
    }

    private fun startUnlockWithPasswordActivity() {
        val intent = Intent(this, UnlockWithPasswordActivity::class.java)
        startActivity(intent)
    }
}
