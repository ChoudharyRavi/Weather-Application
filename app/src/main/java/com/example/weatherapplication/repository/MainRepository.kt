package com.example.weatherapplication.repository

import com.example.weatherapplication.interfaces.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getWeatherUpdate() = retrofitService.getWeatherUpdate(12.9082847623315,77.65197822993314
    ,"imperial","b143bb707b2ee117e62649b358207d3e")
}