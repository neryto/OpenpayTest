package com.example.openpaytest.ui.gallery

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.openpaytest.R
import com.example.openpaytest.base.BaseFragment
import com.example.openpaytest.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : BaseFragment() {

    private lateinit var binding: FragmentGalleryBinding
    private val viewModel: GalleryViewModel by viewModels()


    override fun setLayout(): Int = R.layout.fragment_gallery

    override fun setupView(view: View) {
        binding = FragmentGalleryBinding.bind(view).apply {
            fabPickImage.setOnClickListener {
                pickImageFromGallery()
            }
        }
    }

    private val pickImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = result.data?.data
                imageUri?.let { uri ->
                    lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            viewModel.saveImage(uri)
                        }
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Error al seleccionar la imagen",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    override fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.saveImageResult.collect {
                        if (it) Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT)
                            .show()

                    }
                }

                launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.loading.collect {
                        showProgress(it)
                        }
                    }
                }

            }
        }


    }

    private fun showProgress(show : Boolean) {
        with(binding.progressIndicator){
            if (show){
                if (visibility == View.GONE) visibility = View.VISIBLE

            }else{
                if (visibility == View.VISIBLE) visibility = View.GONE

            }
        }
    }

    override fun startFragmentActions() {

    }


    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImage.launch(intent)
    }
}