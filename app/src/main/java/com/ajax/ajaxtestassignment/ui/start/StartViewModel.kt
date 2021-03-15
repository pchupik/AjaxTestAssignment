package com.ajax.ajaxtestassignment.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajax.ajaxtestassignment.data.User
import com.ajax.ajaxtestassignment.repository.UsersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class StartViewModel @Inject constructor(val repository: UsersRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()

    val users : LiveData<List<User>>
    get() {
        _users.loadUsers()
        return _users
    }

    private fun MutableLiveData<List<User>>.loadUsers() {
        viewModelScope.launch {
            value = repository.getUsers()
        }
    }

    fun reload() {
        viewModelScope.launch {
            _users.value = repository.reload()
        }
    }

    fun delete(user: User) {
        viewModelScope.launch {
            _users.value = repository.delete(user)
        }
    }
}