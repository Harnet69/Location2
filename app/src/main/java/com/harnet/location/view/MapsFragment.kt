package com.harnet.location.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.harnet.location.R
import com.harnet.location.model.AppPermissions
import com.harnet.location.service.PermissionService
import com.harnet.location.viewModel.MapsViewModel

class MapsFragment : Fragment() {

    private lateinit var viewModel: MapsViewModel

    private val callback = OnMapReadyCallback { googleMap ->
        observeViewModel(googleMap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MapsViewModel::class.java)

        // check if permission have been granted already
        if (this.context?.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            viewModel.refreshUsersCoords(activity as Activity)
        } else {
            (activity as MainActivity).appPermissions.locationService.checkPermission()
        }

//        activity?.let { viewModel.refreshUsersCoords(it) }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun observeViewModel(googleMap: GoogleMap) {

        viewModel.mUserCoords.observe(viewLifecycleOwner, Observer { userCoords ->
            userCoords?.let {
                googleMap.addMarker(MarkerOptions().position(it).title("User"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 15F))
            }
        })
    }

    // method is called when activity get a result of user  permission decision
    fun onPermissionsResult(permissionGranted: Boolean) {
        if (permissionGranted) {
            viewModel.refreshUsersCoords(activity as Activity)
        }
    }
}