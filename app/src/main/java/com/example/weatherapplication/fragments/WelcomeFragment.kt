package com.example.weatherapplication.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentLoginBinding
import com.example.weatherapplication.databinding.FragmentWelcomeBinding
import com.example.weatherapplication.manager.PrefManger

class WelcomeFragment : Fragment() {
    private var binding: FragmentWelcomeBinding? = null
    private lateinit var prefManager: PrefManger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefManager = PrefManger(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btnLogin.setOnClickListener{
                Log.w("msg","btn login clicked!!")
                findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
            }
        }
        checkLogin()
    }

    private fun checkLogin() {
        if (prefManager.isLogin()!!){
            findNavController().navigate(R.id.action_welcomeFragment_to_userListFragment)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}