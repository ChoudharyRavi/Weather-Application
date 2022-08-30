package com.example.weatherapplication.viewmodel

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapplication.model.WeatherResponse
import com.example.weatherapplication.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val dataList = MutableLiveData<WeatherResponse>()
    val errorMessage = MutableLiveData<String>()

    fun getWeatherUpdate() {
        Log.d("msg", "getWeatherUpdate")
        val response = repository.getWeatherUpdate()
        response.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                dataList.postValue(response.body())
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("msg", "onFailure ${t.message}")

                errorMessage.postValue(t.message)
            }
        })
    }
}