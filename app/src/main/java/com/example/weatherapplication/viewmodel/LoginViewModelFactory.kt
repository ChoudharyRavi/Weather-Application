package com.example.weatherapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapplication.interfaces.LoginResultCallBacks

class LoginViewModelFactory constructor(private val listener: LoginResultCallBacks): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(this.listener) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}