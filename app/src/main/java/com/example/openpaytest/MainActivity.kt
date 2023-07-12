package com.example.openpaytest

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnPreDrawListener
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.openpaytest.base.ErrorDialogFragment
import com.example.openpaytest.base.ErrorDialogFragment.Companion.TAG
import com.example.openpaytest.common.PermissionsHandlerViewModel
import com.example.openpaytest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: PermissionsHandlerViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                viewModel.updatePermissionValue(true)
            } else {
                showError(getString(R.string.required_permission))

            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_profile,
                R.id.navigation_movies,
                R.id.navigation_map,
                R.id.navigation_register_location,
                R.id.navigation_gallery
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.launchPermissionsRequest.collect {
                        if (it.second) requestPermission(it.first)
                    }
                }
            }
        }
    }

    private fun requestPermission(permission: String) {
        when {
            ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.updatePermissionValue(true)
            }

            shouldShowRequestPermissionRationale(permission) -> {
                showError(getString(R.string.required_permission))

            }

            else -> {
                requestPermissionLauncher.launch(
                    permission
                )
            }
        }
    }

    private fun showError(message: String) {
        val dialogFragment = ErrorDialogFragment.newInstance(message)
        dialogFragment.show(supportFragmentManager, TAG)
    }


}