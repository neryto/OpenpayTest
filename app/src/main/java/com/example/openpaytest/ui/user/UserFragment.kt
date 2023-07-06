package com.example.openpaytest.ui.user

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.openpaytest.R
import com.example.openpaytest.base.BaseFragment
import com.example.openpaytest.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : BaseFragment() {

    private lateinit var binding: FragmentUserBinding
    override fun setLayout(): Int  = R.layout.fragment_user

    private val viewModel : UserViewModel by viewModels()

    override fun setupView(view: View) {
        binding = FragmentUserBinding.bind(view)


    }

    override fun initObservers() {
        lifecycleScope.launch {
            viewModel.text.collect{
                binding.usernameTextView.text = it
            }
        }

    }

    override fun startFragmentActions() {

    }



}