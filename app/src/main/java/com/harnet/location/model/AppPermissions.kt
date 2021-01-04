package com.harnet.location.model

import android.app.Activity
import androidx.fragment.app.Fragment
import com.harnet.location.service.LocationService

data class AppPermissions(val activity: Activity,val fragment: Fragment){
    val locationService: LocationService = LocationService(activity, fragment)
}