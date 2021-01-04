package com.harnet.location.model

import android.app.Activity
import androidx.fragment.app.Fragment
import com.harnet.location.service.LocationPermission

data class AppPermissions(val activity: Activity,val fragment: Fragment){
    val locationPermission: LocationPermission = LocationPermission(activity, fragment)
}