package com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.screens

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.R
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common.Injection
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common.SharedPrefs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val sharedPrefs: SharedPrefs by lazy { Injection().provideSharePreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpLoginBtn()
    }

    private fun setUpLoginBtn() {
        loginBtn.setOnClickListener {
            goToNextScreen()
        }
    }

    private fun goToNextScreen() {
        sharedPrefs.appInForeground = true
        val intent = Intent(this, PersonalInfoActivity::class.java)
        startActivity(intent)
    }
}
