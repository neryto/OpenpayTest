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
import com.example.openpaytest.ui.movies.MovieAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
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

    override fun initCollectors() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.user.collect{user->
                        user?.let { notNullUser->
                            with(binding){
                                usernameTextView.text = notNullUser.name
                                imageViewAvatar
                                    .loadImage(
                                        "${BuildConfig.IMAGE_URL}${notNullUser.avatarUrl}"
                                    )
                            }

                        }
                    }
                }

                launch {
                    viewModel.ratedMovies.collect{
                        binding.recyclerView.apply {
                            adapter = MovieAdapter(it)
                            layoutManager = FlexboxLayoutManager(
                                requireContext(),
                                FlexDirection.COLUMN
                            )
                        }
                    }
                }

                launch {
                    viewModel.error.collect{
                        it?.let {notNullError->
                            showError(notNullError)
                        }
                    }
                }
            }
        }
    }

    override fun startFragmentActions() {
        viewModel.resetFlowValues()
        lifecycleScope.launch {
            viewModel.getUser()
        }

        lifecycleScope.launch {
            viewModel.getRatedMovies()
        }
    }



}