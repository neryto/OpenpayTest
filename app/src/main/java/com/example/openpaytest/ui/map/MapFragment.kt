package com.example.openpaytest.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.openpaytest.databinding.FragmentMapBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    lateinit var binding: FragmentMapBinding
    private lateinit var mMap: GoogleMap

    private val viewModel : MapViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        configureMapView(savedInstanceState)
        return binding.root
    }

    private fun configureMapView(savedInstanceState: Bundle?) {
        with(binding.mapView) {
            onCreate(savedInstanceState)
            onResume()
            activity?.applicationContext?.let { notNullContext ->
                MapsInitializer.initialize(notNullContext)

            }
            getMapAsync(this@MapFragment)
        }

    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        lifecycleScope.launch {
            viewModel.getLocations()
        }
    }

}