package com.example.weatherapplication.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.databinding.FragmentAddNewUserBinding
import com.example.weatherapplication.model.User
import com.example.weatherapplication.model.UserDetails
import com.example.weatherapplication.viewmodel.UserViewModel

class AddNewUserFragment : Fragment() {

    private var _binding: FragmentAddNewUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewUserBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnSave.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root

    }

    private fun insertDataToDatabase() {
        val firstName = binding.edtFirstName.text.toString()
        val lastName = binding.edtLastName.text.toString()
        val email = binding.edtEmailId.text.toString()

        if(inputCheck(firstName, lastName, email)) {
            // Create User Object
            val user = UserDetails(0, firstName, lastName, email)

            // Add Data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.action_addNewUserFragment_to_userListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, email: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(email))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}