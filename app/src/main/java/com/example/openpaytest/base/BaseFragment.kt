package com.example.openpaytest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.openpaytest.base.ErrorDialogFragment.Companion.TAG

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
    abstract fun initCollectors()
    abstract fun startFragmentActions()

    override fun onStart() {
        super.onStart()
        initCollectors()
    }

   open fun showError(message : String){
       val dialogFragment = ErrorDialogFragment.newInstance(message)
       dialogFragment.show(childFragmentManager, TAG)
   }

}