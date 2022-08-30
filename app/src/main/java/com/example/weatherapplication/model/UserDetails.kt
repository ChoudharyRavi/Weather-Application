package com.example.weatherapplication.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_table") // User Entity represents a table within the database.
data class UserDetails( @PrimaryKey(autoGenerate = true)
                        val id: Int, // <- 'id' is the primary key which will be autogenerated by the Room library.
                        val firstName: String,
                        val lastName: String,
                        val email: String): Parcelable
