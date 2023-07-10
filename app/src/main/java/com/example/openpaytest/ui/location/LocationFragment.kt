package com.example.openpaytest.ui.location

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.openpaytest.R
import com.example.openpaytest.base.BaseFragment
import com.example.openpaytest.common.LocationReceiver
import com.example.openpaytest.common.LocationService
import com.example.openpaytest.common.LocationService.Companion.ACTION_SEND_LOCATION
import com.example.openpaytest.common.NotificationHandler
import com.example.openpaytest.common.PermissionsHandlerViewModel
import com.example.openpaytest.databinding.FragmentRegisterLocationBinding
import com.example.openpaytest_data.models.LocationItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LocationFragment : BaseFragment(),
    LocationReceiver.DataListener {

    @Inject
    lateinit var notificationHandler: NotificationHandler

    lateinit var binding: FragmentRegisterLocationBinding
    private lateinit var locationReceiver: LocationReceiver


    private val locationPermissionsHandlerViewModel:
            PermissionsHandlerViewModel by activityViewModels()

    private val viewModel: LocationViewModel by viewModels()

    override fun setLayout(): Int = R.layout.fragment_register_location

    override fun setupView(view: View) {
        binding = FragmentRegisterLocationBinding.bind(view)
    }

    override fun initCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    locationPermissionsHandlerViewModel.permissionsGranted.collect {
                        if (it) initLocationService()

                    }
                }

                launch {
                    viewModel.saveLocationResult.collect {
                        it?.let { location ->
                            notificationHandler.showNotification(
                                getString(R.string.notification_title),
                                getString(
                                    R.string.notification_message,
                                    location.latitude,
                                    location.longitude
                                )
                            )
                            viewModel.resetFlowValues()
                        }

                    }
                }

            }

        }

    }

    private fun initLocationService() {
        Intent(requireContext(), LocationService::class.java).apply {
            requireActivity().startService(this)
        }
    }

    override fun startFragmentActions() {
        locationReceiver = LocationReceiver(this)

        LocalBroadcastManager
            .getInstance(requireContext())
            .registerReceiver(
                locationReceiver,
                IntentFilter(ACTION_SEND_LOCATION)
            )

        locationPermissionsHandlerViewModel
            .updateLaunchPermissionRequestValue(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                true
            )
    }

    override fun onDataReceived(data: LocationItem) {
        lifecycleScope.launch {
            viewModel.saveLocation(data)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(locationReceiver)
    }

}