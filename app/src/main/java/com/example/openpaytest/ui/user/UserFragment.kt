package com.example.openpaytest.ui.user

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.openpaytest.BuildConfig
import com.example.openpaytest.R
import com.example.openpaytest.base.BaseFragment
import com.example.openpaytest.databinding.FragmentUserBinding
import com.example.openpaytest.extensions.loadImage
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
            viewModel.user.collect{user->
                user?.let { notNullUser->
                    with(binding){
                        usernameTextView.text = notNullUser.name
                        imageViewAvatar
                            .loadImage("${BuildConfig.IMAGE_URL}${notNullUser.avatarUrl}")
                    }

                }
            }
        }

    }

    override fun startFragmentActions() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getUser()
            }
        }
    }



}