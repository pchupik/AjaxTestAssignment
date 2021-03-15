package com.ajax.ajaxtestassignment.di

import androidx.fragment.app.Fragment
import com.ajax.ajaxtestassignment.ui.base.BaseFragment
import com.ajax.ajaxtestassignment.ui.details.DetailsFragment
import com.ajax.ajaxtestassignment.ui.details.DetailsViewModel
import com.ajax.ajaxtestassignment.ui.start.StartFragment
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

//    fun inject(fragment: BaseFragment)

    fun inject(fragment: StartFragment)
    fun inject(fragment: DetailsFragment)
}