package com.ajax.ajaxtestassignment

import android.app.Application
import com.ajax.ajaxtestassignment.di.ApplicationComponent
import com.ajax.ajaxtestassignment.di.ApplicationModule
import com.ajax.ajaxtestassignment.di.DaggerApplicationComponent

class AjaxTestApp : Application() {

    var appComponent : ApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()

    override fun onCreate() {
        super.onCreate()

    }
}