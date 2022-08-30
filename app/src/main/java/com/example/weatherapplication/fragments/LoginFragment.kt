package com.example.weatherapplication.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentLoginBinding
import com.example.weatherapplication.interfaces.LoginResultCallBacks
import com.example.weatherapplication.manager.PrefManger
import com.example.weatherapplication.model.User
import com.example.weatherapplication.viewmodel.LoginViewModel
import com.example.weatherapplication.viewmodel.LoginViewModelFactory

class LoginFragment : Fragment(), LoginResultCallBacks {

    private lateinit var viewModel: LoginViewModel
    private lateinit var prefManger: PrefManger
    private var binding: FragmentLoginBinding? = null
    private lateinit var user: User
    private lateinit var mail: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(
                this,
                LoginViewModelFactory(this@LoginFragment)
            )[LoginViewModel::class.java]
        }!!
        prefManger = PrefManger(requireActivity())
    }


    override fun onSuccess(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bindingFragment = FragmentLoginBinding.inflate(inflater, container, false)
        binding = bindingFragment

        bindingFragment.viewModel = viewModel
        onClickListeners(bindingFragment)

        return bindingFragment.root

    }


    private fun onClickListeners(binding: FragmentLoginBinding) {
        binding.btnLogin.setOnClickListener {
            mail = binding.edtEmail.text.toString()
            password = binding.edtPassword.text.toString()
            user = User(mail,password)
            when (user.isDataValid()) {
                0 -> onError("e-mail should not be empty")
                1 -> onError("It is not a correct email address")
                2 -> onError("\n" + "The length of the password must be greater than 6")
                else -> {
                    onSuccess("Successful login")
                    findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
                    prefManger.setLogin(true)
                }

            }
        }
    }

}