package com.scalar.scalar.helpers

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.scalar.scalar.R

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences
    private val appContext: Context = context.applicationContext

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext)
    }

    val doesPinExist: Boolean
    get() = sharedPreferences.getBoolean(appContext.getString(R.string.preference_pin_exists), false)
}
