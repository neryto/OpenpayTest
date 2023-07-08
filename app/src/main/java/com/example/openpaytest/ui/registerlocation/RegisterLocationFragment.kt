package com.example.openpaytest.ui.registerlocation

import android.Manifest
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.openpaytest.R
import com.example.openpaytest.base.BaseFragment
import com.example.openpaytest.common.LocationPermissionsHandlerViewModel
import com.example.openpaytest.databinding.FragmentRegisterLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterLocationFragment : BaseFragment() {

    lateinit var binding: FragmentRegisterLocationBinding

    private val locationPermissionsHandlerViewModel:
            LocationPermissionsHandlerViewModel by activityViewModels()

    private val viewModel : RegisterLocationViewModel by viewModels()


    override fun setLayout(): Int = R.layout.fragment_register_location

    override fun setupView(view: View) {
        binding = FragmentRegisterLocationBinding.bind(view)
    }

    override fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                locationPermissionsHandlerViewModel.permissionsGranted.collect{
                    if (it) initLocationWorker()

                }
            }

        }

    }

    private fun initLocationWorker() {
        Log.e("MYLOCATION", "initLocationWorker")


    }

    override fun startFragmentActions() {
        locationPermissionsHandlerViewModel
            .updateLaunchPermissionRequestValue(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                true
            )
    }

}