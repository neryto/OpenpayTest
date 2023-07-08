package com.example.openpaytest.ui.registerlocation

import android.view.View
import com.example.openpaytest.R
import com.example.openpaytest.base.BaseFragment
import com.example.openpaytest.databinding.FragmentRegisterLocationBinding

class RegisterLocationFragment : BaseFragment() {

    lateinit var binding : FragmentRegisterLocationBinding

    override fun setLayout(): Int  = R.layout.fragment_register_location

    override fun setupView(view: View) {
        binding = FragmentRegisterLocationBinding.bind(view)
    }

    override fun initObservers() {
    }

    override fun startFragmentActions() {
    }

}