package com.harnet.location.service

import android.Manifest
import android.app.Activity
import androidx.fragment.app.Fragment
import com.harnet.location.model.Permissions

class LocationPermission(activity: Activity, fragment: Fragment): PermissionService(activity, fragment){
    override val permissionCode: Int = Permissions.LOCATION.permCode
    override val permissionType = Manifest.permission.ACCESS_FINE_LOCATION
    override val rationaleTitle = Permissions.LOCATION.rationaleTitle
    override val rationaleMessage = Permissions.LOCATION.rationaleMessage
}