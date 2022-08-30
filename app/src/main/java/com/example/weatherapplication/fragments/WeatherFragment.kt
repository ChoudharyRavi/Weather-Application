package com.example.weatherapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.adapters.MainAdapter
import com.example.weatherapplication.databinding.FragmentUserListBinding
import com.example.weatherapplication.databinding.FragmentWeatherBinding
import com.example.weatherapplication.interfaces.RetrofitService
import com.example.weatherapplication.manager.PrefManger
import com.example.weatherapplication.repository.MainRepository
import com.example.weatherapplication.viewmodel.MainViewModel
import com.example.weatherapplication.viewmodel.MyViewModelFactory

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private lateinit var prefManger: PrefManger
    private val binding get() = _binding!!
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService)))[(MainViewModel::class.java)]
        prefManger = PrefManger(requireActivity())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("msg", "onCreateView")
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_logout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if (item.itemId == R.id.weatherFragment) {
            prefManger.removeData()
            findNavController().navigate(R.id.action_weatherFragment_to_loginFragment)
        } else if (item.itemId == androidx.appcompat.R.id.home) {
            requireActivity().finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpRecyclerView() {
        Log.d("msg", "setUpRecyclerView")

        binding.recyclerview.adapter = adapter
        viewModel.dataList.observe(requireActivity(), Observer {
            Log.d("msg", "onCreate: $it")
            adapter.setWeatherReport(listOf(it))
        })
        viewModel.errorMessage.observe(requireActivity(), Observer {
        })
        viewModel.getWeatherUpdate()
    }
}