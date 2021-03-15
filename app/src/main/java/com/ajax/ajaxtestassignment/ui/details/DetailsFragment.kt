package com.ajax.ajaxtestassignment.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.ajax.ajaxtestassignment.AjaxTestApp
import com.ajax.ajaxtestassignment.R
import com.ajax.ajaxtestassignment.di.DetailsViewModelFactory
import com.ajax.ajaxtestassignment.di.StartViewModelFactory
import com.ajax.ajaxtestassignment.ui.base.BaseFragment
import com.ajax.ajaxtestassignment.ui.start.StartViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject


open class DetailsFragment : BaseFragment() {

    @Inject
    lateinit var factory: DetailsViewModelFactory

    private val viewModel by viewModels<DetailsViewModel> { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AjaxTestApp).appComponent.inject(this)
    }

    companion object {

        const val USER_ID_KEY = "userId"
        fun newInstance(userId: String): DetailsFragment {
            val args = Bundle()
            args.putString(USER_ID_KEY, userId)

            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var userId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = arguments?.getString(USER_ID_KEY) ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getUser(userId).observe(viewLifecycleOwner, {
            firstName.setText(it.firstName)
            lastName.setText(it.lastName)
            email.setText(it.email)
            Picasso.with(context)
                .load(it.photo)
                .into(image)
        })

        viewModel.isEditing.observe(viewLifecycleOwner, {
            saveButton.isEnabled = it
            cancelButton.isEnabled = it
        })

        firstName.doAfterTextChanged {
            viewModel.updateFirstName(it.toString())
        }

        lastName.doAfterTextChanged {
            viewModel.updateLastName(it.toString())
        }

        email.doAfterTextChanged {
            viewModel.updateEmail(it.toString())
        }

        saveButton.setOnClickListener { viewModel.save() }

        cancelButton.setOnClickListener { viewModel.cancel() }
    }
}