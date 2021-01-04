package com.harnet.location.service

import android.Manifest
import android.app.Activity
import androidx.fragment.app.Fragment
import com.harnet.location.R

class LocationService(activity: Activity, fragment: Fragment) :
    PermissionService(activity, fragment){
    override val permissionCode: Int =
        activity.resources.getString(R.string.permissionNavigation).toInt()
    override val permissionType = Manifest.permission.ACCESS_FINE_LOCATION
    override val rationaleTitle = "Access to a location"
    override val rationaleMessage = "If you want to use the app, you should give an access to your location"
}