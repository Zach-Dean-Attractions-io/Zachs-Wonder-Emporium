package com.example.zachswonderemporium.ui.explore

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.CoroutinesRoom
import com.example.zachswonderemporium.R
import com.example.zachswonderemporium.data.Repository
import com.example.zachswonderemporium.data.local.ZachsWonderEmporiumDatabase
import com.example.zachswonderemporium.data.model.PointOfInterest
import com.example.zachswonderemporium.data.model.PointOfInterestCategory
import com.example.zachswonderemporium.databinding.FragmentExploreBinding
import com.example.zachswonderemporium.ui.ViewModelFactory
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import java.lang.IllegalStateException
import kotlin.coroutines.CoroutineContext

// Set Device Location To: 52.98745595586085, -1.8863546423617787

class ExploreFragment : Fragment(), OnMapReadyCallback {

    private lateinit var exploreViewModel: ExploreViewModel
    private var _binding: FragmentExploreBinding? = null

    // Ensure we don't add markers to the map before it is initialised. For example the viewModel returns data before OnMapReadyCallback occurs
    private var googleMap: GoogleMap? = null
    private var defaultZoomLevel = 15.0f
    private var defaultPadding = 250

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    // Current Points Of Interest
    private var pointsOfInterest: List<PointOfInterest>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Repository DI
        val repository: Repository by inject()

        exploreViewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(ExploreViewModel::class.java)

        exploreViewModel.pointsOfInterestLive.observe(viewLifecycleOwner) { pointsOfInterest ->
            this.pointsOfInterest = pointsOfInterest
            displayPointsOfInterestOnMap()
        }

        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.exploreMapView.onCreate(savedInstanceState)
        binding.exploreMapView.getMapAsync(this)

        // Category Selector Setup
        binding.poiCategorySelectorChipgroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.poi_category_attraction -> exploreViewModel.retrievePointsOfInterestByCategory(PointOfInterestCategory.ATTRACTIONS)
                R.id.poi_category_show -> exploreViewModel.retrievePointsOfInterestByCategory(PointOfInterestCategory.SHOWS)
                R.id.poi_category_food_and_drink -> exploreViewModel.retrievePointsOfInterestByCategory(PointOfInterestCategory.FOOD_AND_DRINK)
                else -> Log.e(TAG, "Unknown Chip Selected")
            }
        }

        // Set Chip Font (Doesn't Seem To Work Via XML)
        binding.poiCategorySelectorChipgroup.children.forEach { chip ->
            if(chip is Chip) {
                chip.typeface = ResourcesCompat.getFont(requireContext(), R.font.acme)
            }
        }
    }
    
    private fun displayPointsOfInterestOnMap() {
        googleMap?.let { googleMap ->

            println("Display $pointsOfInterest")

            // Clear Current Markers
            googleMap.clear()

            // Camera Update Bounds
            var cameraUpdateBounds = LatLngBounds.Builder()

            // Add New Markers
            pointsOfInterest?.forEach { pointOfInterest ->
                googleMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(pointOfInterest.latitude, pointOfInterest.longitude))
                        .title(pointOfInterest.name)
                )
                cameraUpdateBounds.include(LatLng(pointOfInterest.latitude, pointOfInterest.longitude))
            }

           try {
               googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraUpdateBounds.build(), defaultPadding))
           } catch(e: IllegalStateException) {
               // This occurs when the cameraUpdateBounds has no points in it.
           }

        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        setMapStyle(map)

        setMapBoundsAndCenter(map)

        // Set Map UI Settings
        map.uiSettings.isZoomControlsEnabled = true

        // Location Permissions and Device Location Checks
        checkLocationPermission()

        // Add Markers To Map (If ViewModel Returned Before Map Ready)
        displayPointsOfInterestOnMap()
    }

    private fun setMapBoundsAndCenter(map: GoogleMap) {
        var zachsWonderEmporiumBounds = LatLngBounds(
            LatLng(52.98368007907967, -1.8979389829144042),
            LatLng(52.991434650908005, -1.8727104029116646)
        )
        map.setLatLngBoundsForCameraTarget(zachsWonderEmporiumBounds)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(zachsWonderEmporiumBounds.center, defaultZoomLevel))
    }

    private val settingsRequest = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        // If the user has come from the 'SETTINGS' click in the Snackbar. Check Location Permissions
        checkLocationPermission()
    }

    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

            permissions.forEach { (permission, wasGranted) ->
                // Handle Location Permission Result
                if(permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if(wasGranted) {
                        // Check Device Location Setting
                        checkDeviceLocationSetting()
                    } else {
                        // Show SnackBar Saying App Is Better With Location
                        Snackbar.make(binding.root, getString(R.string.no_location_permission_snackbar), Snackbar.LENGTH_SHORT)
                            .setAction("Settings") {
                                var settingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                var appUri = Uri.fromParts("package", requireContext().packageName, null)
                                settingsIntent.data = appUri

                                settingsRequest.launch(settingsIntent)
                            }
                            .show()
                    }
                }
            }
        }

    @SuppressLint("MissingPermission")
    private val deviceLocationRequest = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if(result.resultCode == RESULT_CANCELED) {
           // Device Location Not Turned On
            Snackbar.make(binding.root, getString(R.string.device_location_setting_off), Snackbar.LENGTH_SHORT).show()
        } else {
            displayPointsOfInterestOnMap()
            googleMap?.isMyLocationEnabled = true
        }
    }

    // Check Location Permissions
    private fun checkLocationPermission() {
        if(isLocationPermissionGranted()) {
            // Check To See If Device Location Setting Is On
            checkDeviceLocationSetting()
        } else {
            // Request Location Permission
            permissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
        }
    }

    // Location Permission
    private fun isLocationPermissionGranted() = ActivityCompat.checkSelfPermission(requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    fun checkDeviceLocationSetting() {
        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_LOW_POWER
        }
        val locationRequestBuilder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val settingsClient = LocationServices.getSettingsClient(requireActivity())

        settingsClient.checkLocationSettings(locationRequestBuilder.build()).apply {
            addOnFailureListener { exception ->
                // We Enter Here If The Device Location Setting Is Off
                if(exception is ResolvableApiException) {
                    // Ask The User To Turn Their Device Location On
                    var intentSenderRequest = IntentSenderRequest.Builder(exception.resolution.intentSender).build()
                    deviceLocationRequest.launch(intentSenderRequest)
                } else {
                    // Error!
                    Snackbar.make(binding.root, getString(R.string.device_location_setting_error), Snackbar.LENGTH_SHORT).show()
                }
            }
            addOnSuccessListener {
                displayPointsOfInterestOnMap()
                googleMap?.isMyLocationEnabled = true
            }
        }

    }

    private fun setMapStyle(map: GoogleMap) {
        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.google_map_style)
            )
            if(!success) {
                Log.e(TAG, "Style Parsing Failed")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't Find Style! Error: ", e)
        }
    }

    // When using a MapView rather than SupportMapFragment we need to override these methods and call the corresponding method on the MapView object
    // The onDestroy() method is called from onDestroyView() since the binding is made null there and then we get a NullPointerException trying it in onDestroy
    override fun onStart() {
        super.onStart()
        _binding?.let {
            it.exploreMapView.onStart()
        }
    }

    override fun onResume() {
        super.onResume()
        _binding?.let {
            it.exploreMapView.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        _binding?.let {
            it.exploreMapView.onPause()
        }
    }

    override fun onStop() {
        super.onStop()
        _binding?.let {
            it.exploreMapView.onStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.let {
            it.exploreMapView.onDestroy()
        }
        _binding = null
    }

    override fun onLowMemory() {
        super.onLowMemory()
        _binding?.let {
            it.exploreMapView.onLowMemory()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        _binding?.let {
            it.exploreMapView.onSaveInstanceState(outState)
        }
    }



}

const val TAG = "ExploreFragment"