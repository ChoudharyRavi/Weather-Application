package com.example.weatherapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.databinding.AdapterMainBinding
import com.example.weatherapplication.model.Current
import com.example.weatherapplication.model.WeatherResponse

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {
    var currentData = mutableListOf<WeatherResponse>()

    fun setWeatherReport(weatherData: List<WeatherResponse>) {
        this.currentData = weatherData.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val current = currentData[position]
        holder.binding.txtTemp.text = "Current Temp = " + current.current.temp.toString()
        holder.binding.txtWeatherMain.text = "Current Main =  " + current.current.weather[position].main
        holder.binding.txtWeatherDescp.text = "Current Weather Description =  " + current.current.weather[position].description
        holder.binding.txtHumidity.text = "Current Humidity =  " + current.current.humidity.toString()
        holder.binding.txtWindSpeed.text = "Current WindSpeed = " + current.current.windSpeed.toString()

    }
    override fun getItemCount(): Int {
        return currentData.size
    }
}
class MainViewHolder(val binding: AdapterMainBinding) : RecyclerView.ViewHolder(binding.root) {
}