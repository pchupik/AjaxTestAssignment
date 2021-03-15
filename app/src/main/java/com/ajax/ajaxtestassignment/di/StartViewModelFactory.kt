package com.ajax.ajaxtestassignment.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajax.ajaxtestassignment.ui.start.StartViewModel
import javax.inject.Inject

class StartViewModelFactory @Inject constructor(private val vm: StartViewModel) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return vm as T
    }
}