package com.ajax.ajaxtestassignment.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ajax.ajaxtestassignment.data.User
import com.ajax.ajaxtestassignment.repository.UsersRepository
import com.ajax.ajaxtestassignment.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(val repository: UsersRepository) : BaseViewModel() {

    private val user = MutableLiveData<User>()

    fun getUser(uuid: String) : LiveData<User> {
        refreshUser(uuid)
        return user
    }

    private fun refreshUser(uuid: String) {
        viewModelScope.launch {
            user.value = repository.getUser(uuid)
        }
    }

    fun updateFirstName(text: String){
        if (user.value?.firstName != text) {
            user.value?.firstName = text
            _isEditing.value = true
        }
    }

    fun updateLastName(text: String){
        if (user.value?.lastName != text) {
            user.value?.lastName = text
            _isEditing.value = true
        }
    }

    fun updateEmail(text: String){
        if (user.value?.email != text) {
            user.value?.email = text
            _isEditing.value = true
        }
    }

    private val _isEditing = MutableLiveData<Boolean>(false)

    val isEditing : LiveData<Boolean> = _isEditing

    fun save(){
        _isEditing.value = false
        user.value?.let {
            viewModelScope.launch {
                repository.updateUser(it)
            }
        }
    }

    fun cancel(){
        _isEditing.value = false
        user.value?.let {
            refreshUser(it.uuid)
        }
    }

    fun delete() {
        user.value?.let {
            viewModelScope.launch {
                repository.delete(it)
            }
        }
    }

}