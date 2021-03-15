package com.ajax.ajaxtestassignment.api

import android.util.Log
import com.ajax.ajaxtestassignment.data.UserDTO
import javax.inject.Inject

class UserApi @Inject constructor(private val service: UserService) {

    suspend fun loadUsers() : List<UserDTO> = try {
        service.users(30).results
    } catch (e: Exception){
        Log.e("UserApi", "", e)
        listOf()
    }
}