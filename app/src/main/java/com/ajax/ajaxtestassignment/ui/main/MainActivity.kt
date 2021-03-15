package com.ajax.ajaxtestassignment.ui.main

import android.os.Bundle
import com.ajax.ajaxtestassignment.R
import com.ajax.ajaxtestassignment.data.User
import com.ajax.ajaxtestassignment.ui.base.BaseActivity
import com.ajax.ajaxtestassignment.ui.details.DetailsFragment
import com.ajax.ajaxtestassignment.ui.start.StartFragment


class MainActivity : BaseActivity(), DetailOpener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val startFragment = StartFragment();
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                startFragment
            ).commit();
        }
    }

    override fun openDetails(user: User) {
        val fragment = DetailsFragment.newInstance(user.uuid)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}