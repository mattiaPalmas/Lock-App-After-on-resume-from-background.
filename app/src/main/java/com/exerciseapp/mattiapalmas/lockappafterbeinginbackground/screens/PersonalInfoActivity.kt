package com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.screens

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.R
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common.Injection
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common.LoggedInBaseActivity
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common.SharedPrefs
import kotlinx.android.synthetic.main.activity_personal_info.*

class PersonalInfoActivity : LoggedInBaseActivity() {

    private val sharedPrefs: SharedPrefs by lazy { Injection().provideSharePreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)

        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        setUpCheckCreditBtn()
    }

    override fun onBackPressed() {
        setAppInForeground(true)
        super.onBackPressed()
    }

    private fun setUpCheckCreditBtn() {
        checkCreditBtn.setOnClickListener {
            goToCreditScreen()
        }
    }

    override fun onStop() {
        setAppInForeground(false)
        super.onStop()
    }

    private fun goToCreditScreen() {
        setAppInForeground(true)
        val intent = Intent(this, CheckCreditActivity::class.java)
        startActivity(intent)
    }

    private fun setAppInForeground(boolean: Boolean) {
        sharedPrefs.appInForeground = boolean
    }
}
