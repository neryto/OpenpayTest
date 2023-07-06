package com.example.openpaytest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        layoutInflater.inflate(setLayout(), container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        startFragmentActions()
    }

    abstract fun setLayout(): Int
    abstract fun setupView(view: View)
    abstract fun initObservers()
    abstract fun startFragmentActions()

    override fun onStart() {
        super.onStart()
        initObservers()
    }


}