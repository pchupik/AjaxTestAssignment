package com.ajax.ajaxtestassignment.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajax.ajaxtestassignment.ui.details.DetailsViewModel
import javax.inject.Inject

class DetailsViewModelFactory @Inject constructor(private val vm: DetailsViewModel) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return vm as T
    }
}