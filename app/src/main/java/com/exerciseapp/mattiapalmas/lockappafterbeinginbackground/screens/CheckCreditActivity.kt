package com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.screens

import android.os.Bundle
import android.view.WindowManager
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.R
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common.Injection
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common.LoggedInBaseActivity
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common.SharedPrefs

class CheckCreditActivity : LoggedInBaseActivity() {

    private val sharedPrefs: SharedPrefs by lazy { Injection().provideSharePreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_credit)

        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
    }

    override fun onBackPressed() {
        appIsComingFromBackground(false)
        super.onBackPressed()
    }

    override fun onStop() {
        appIsComingFromBackground(true)
        super.onStop()
    }

    private fun appIsComingFromBackground(boolean: Boolean) {
        sharedPrefs.appIsComingFromBackground = boolean
    }
}
