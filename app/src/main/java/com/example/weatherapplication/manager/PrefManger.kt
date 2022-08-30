package com.example.weatherapplication.manager

import android.content.Context
import android.content.SharedPreferences

class PrefManger(context: Context) {
    val PRIVATE_MODE = 0
    private val PREF_NAME = "SharedPreferences"
    private val IS_LOGIN = "is_login"

    val pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    val editor: SharedPreferences.Editor? = pref?.edit()

    fun setLogin(isLogin: Boolean){
        editor?.putBoolean(IS_LOGIN,isLogin)
        editor?.commit()
    }

    fun isLogin(): Boolean?{
        return pref?.getBoolean(IS_LOGIN,false)
    }

    fun removeData() {
        editor?.clear()
        editor?.commit()
    }
}