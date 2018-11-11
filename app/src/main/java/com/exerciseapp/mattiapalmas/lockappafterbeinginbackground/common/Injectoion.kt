package com.exerciseapp.mattiapalmas.lockappafterbeinginbackground.common

import android.content.Context

class Injection {

    fun provideSharePreference(context: Context): SharedPrefs {
        return SharedPrefs.getInstance(context.getSharedPreferences(Constants.SHARE_PREFS_NAME, Context.MODE_PRIVATE))
    }
}