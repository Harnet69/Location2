package com.harnet.location.view

import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.harnet.location.R
import com.harnet.location.model.MyLocation
import com.harnet.location.viewModel.MapsViewModel

class MapsFragment : Fragment() {
    lateinit var viewModel: MapsViewModel

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

        activity?.let { viewModel.refresh(it) }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun observeViewModel(googleMap: GoogleMap){

        viewModel.mUserCoords.observe(viewLifecycleOwner, Observer { userCoords ->
            userCoords?.let {
            googleMap.addMarker(MarkerOptions().position(userCoords).title("User"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userCoords, 12F))
            }
        })
    }
}