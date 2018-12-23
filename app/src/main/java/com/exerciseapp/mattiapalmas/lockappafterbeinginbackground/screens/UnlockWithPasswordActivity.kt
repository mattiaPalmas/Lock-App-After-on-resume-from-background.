package com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.screens

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.R
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common.Injection
import com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common.SharedPrefs
import kotlinx.android.synthetic.main.activity_unlock_with_password.*

class UnlockWithPasswordActivity : AppCompatActivity() {

    private val sharedPrefs: SharedPrefs by lazy { Injection().provideSharePreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unlock_with_password)

        setUpEnterBtn()
    }

    override fun onBackPressed() {
        //On back pressed is Disabled in this screen.
    }

    private fun setUpEnterBtn() {
        enterBtn.setOnClickListener {
            if (passwordEd.text.toString() == "Password1") {
                sharedPrefs.appIsComingFromBackground = false
                finish()
            } else {
                Toast.makeText(this, getString(R.string.wrong_password), Toast.LENGTH_LONG).show()
            }
        }
    }
}
