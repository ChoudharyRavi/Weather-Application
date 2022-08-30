package com.example.weatherapplication.viewmodel

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.interfaces.LoginResultCallBacks
import com.example.weatherapplication.model.User

class LoginViewModel (private val listener: LoginResultCallBacks): ViewModel(){
    private val user: User = User("","")

    //create function to set Email after user finish enter text
    val emailTextWatcher: TextWatcher
        get()= object:TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            user.setEmail(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }

    //create function to set Password after user finish enter text
    val passwordTextWatcher:TextWatcher
        get()= object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                user.setPassword(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        }

    //create function to process Login Button clicked
    fun onLoginClicked(v: View){
        var loginCode:Int = user.isDataValid()
        if (loginCode == 0)
            listener.onError("e-mail should not be empty")
        else if (loginCode == 1)
            listener.onError("It is not a correct email address")
        else if (loginCode == 2)
            listener.onError("\n" + "The length of the password must be greater than 6")
        else
            listener.onSuccess("Successful login")
    }


}