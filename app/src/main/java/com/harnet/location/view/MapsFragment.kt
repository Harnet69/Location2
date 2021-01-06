package com.harnet.location.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.harnet.location.R
import com.harnet.location.viewModel.MapsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MapsFragment : Fragment() {
    private lateinit var viewModel: MapsViewModel

    private var userMarker: Marker? = null

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
            (activity as MainActivity).appPermissions.getPermissionClass("location", activity as MainActivity, fragment)
                ?.checkPermission()
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun observeViewModel(googleMap: GoogleMap) {

        viewModel.mUserCoords.observe(viewLifecycleOwner, Observer { userCoords ->
//            Toast.makeText(context, userCoords.toString(), Toast.LENGTH_SHORT).show()

            val markerOptions = MarkerOptions()
                .position(userCoords)
                .title("User")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bluedot))
            userCoords.let {
                if (userMarker == null) {
                    userMarker = googleMap.addMarker(markerOptions)
                } else {
                    userMarker?.position = userCoords
                }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 10F))
            }
        })
    }

    // method is called when activity get a result of user permission decision
    fun onPermissionsResult(permissionGranted: Boolean) {
        if (permissionGranted) {
            viewModel.refreshUsersCoords(activity as Activity)
        }
    }
}