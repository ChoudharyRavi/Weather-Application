package com.example.weatherapplication.model

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.databinding.BaseObservable

class User (private var email: String, private  var password: String): BaseObservable() {
    fun isDataValid():Int{
        return if (TextUtils.isEmpty(getEmail()))
            0
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            1
        else if (getPassword().length<7)
            2
        else
            -1
    }



    fun getPassword(): String{
        return  password
    }

    fun getEmail(): String{
         Log.w("msg","email :: $email")
        return  email
    }

    fun setEmail(email: String){
        Log.w("msg","email :: set $email")

        this.email=email
    }
    fun setPassword(password: String){
        this.password=password
    }

}