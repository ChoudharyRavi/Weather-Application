package com.example.weatherapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.R
import com.example.weatherapplication.adapters.ListAdapter
import com.example.weatherapplication.databinding.FragmentUserListBinding
import com.example.weatherapplication.model.UserDetails
import com.example.weatherapplication.viewmodel.UserViewModel


class UserListFragment : Fragment(),ListAdapter.ItemClickListener {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserViewModel
    lateinit var userList: List<UserDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        // RecyclerView
        val adapter = ListAdapter(this)
        val recyclerView = binding.rvUserList
        recyclerView.adapter = adapter

        // UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
            userList = user
        })

        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {

                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        mUserViewModel.deleteUser(userList[direction])

                    Toast.makeText(activity, "User Details Deleted",
                        Toast.LENGTH_SHORT).show()
                }

            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if (item.itemId == R.id.userListFragment) {
            findNavController().navigate(R.id.action_userListFragment_to_addNewUserFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        Log.w("msg","on item clicked!")
        findNavController().navigate(R.id.action_userListFragment_to_weatherFragment)
    }


}