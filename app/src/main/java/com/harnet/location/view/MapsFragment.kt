package com.harnet.location.view

import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.harnet.location.R
import com.harnet.location.model.MyLocation

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // instantiate MyLocation class
        val myLocation = MyLocation()

        val locationResult = object : MyLocation.LocationResult() {
            override fun gotLocation(location: Location?) {
                val lat = location!!.latitude
                val lon = location.longitude
                //TODO Change LiveData here
                Toast.makeText(context, "$lat --SLocRes-- $lon", Toast.LENGTH_SHORT).show()
            }

        }

        myLocation.getLocation(inflater.context, locationResult)

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}