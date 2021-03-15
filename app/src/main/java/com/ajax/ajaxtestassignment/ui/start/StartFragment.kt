package com.ajax.ajaxtestassignment.ui.start

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.ajax.ajaxtestassignment.AjaxTestApp
import com.ajax.ajaxtestassignment.R
import com.ajax.ajaxtestassignment.di.StartViewModelFactory
import com.ajax.ajaxtestassignment.ui.base.BaseFragment
import com.ajax.ajaxtestassignment.ui.main.DetailOpener
import kotlinx.android.synthetic.main.fragment_start.view.*
import javax.inject.Inject


open class StartFragment : BaseFragment() {

    private lateinit var contactAdapter: ContactAdapter

    @Inject
    lateinit var factory: StartViewModelFactory

    private val viewModel by viewModels<StartViewModel> { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AjaxTestApp).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)

        contactAdapter = ContactAdapter(listOf()){
//            Toast.makeText(context!!, it.lastName, Toast.LENGTH_SHORT).show()
            (activity as? DetailOpener)?.openDetails(it)
        }

        // Creates a vertical Layout Manager
        view.contactList.layoutManager = LinearLayoutManager(context)
        view.contactList.adapter = contactAdapter


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.users.observe(viewLifecycleOwner, {
            contactAdapter.items = it
            contactAdapter.notifyDataSetChanged()

        })
    }




}