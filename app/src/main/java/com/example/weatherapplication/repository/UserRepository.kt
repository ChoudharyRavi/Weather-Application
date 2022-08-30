package com.example.weatherapplication.repository

import androidx.lifecycle.LiveData
import com.example.weatherapplication.data.UserDao
import com.example.weatherapplication.model.UserDetails

// User Repository abstracts access to multiple data sources. However this is not the part of the Architecture Component libraries.

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<UserDetails>> = userDao.readAllData()

    suspend fun addUser(user: UserDetails) {
        userDao.addUser(user)
    }

    suspend fun deleteUser(user: UserDetails) {
        userDao.deleteUser(user)
    }
}