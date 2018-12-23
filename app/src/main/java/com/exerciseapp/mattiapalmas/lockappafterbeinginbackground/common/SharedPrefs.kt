package com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common

import android.content.SharedPreferences

class SharedPrefs(private val sharedPref: SharedPreferences) {

    companion object {

        private var INSTANCE: SharedPrefs? = null

        @JvmStatic
        fun getInstance(sp: SharedPreferences): SharedPrefs =
                INSTANCE ?: SharedPrefs(sp).apply { INSTANCE = this }
    }

    var appIsComingFromBackground: Boolean
        get() = sharedPref.getBoolean(Constants.APP_IN_FOREGROUND, true)
        set(value) = sharedPref.edit().putBoolean(Constants.APP_IN_FOREGROUND, value).apply()

}