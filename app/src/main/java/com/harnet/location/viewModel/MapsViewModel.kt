package com.harnet.location.viewModel

import android.app.Activity
import android.app.Application
import android.location.Location
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.harnet.location.model.MyLocation

class MapsViewModel(application: Application): BaseViewModel(application) {
    val mUserCoords = MutableLiveData<LatLng>()

    fun refresh(activity: Activity){
        getUserCoordinates(activity)
    }

    private fun getUserCoordinates(activity: Activity){
        val locationResult = object : MyLocation.LocationResult() {
            override fun gotLocation(location: Location?) {
                val lat = location!!.latitude
                val lon = location.longitude
                //change LiveData here
                mUserCoords.value = LatLng(lat, lon)
            }

        }

        // instantiate MyLocation class
        //TODO Use Dagger to inject the class here
        val myLocation = MyLocation()
        myLocation.getLocation(activity, locationResult)
    }
}