package com.harnet.location.model

import android.app.Activity
import androidx.fragment.app.Fragment
import com.harnet.location.service.LocationPermission
import com.harnet.location.service.PermissionService
import javax.inject.Inject

class AppPermissions @Inject constructor(){
    fun getPermissionClass(permissionName: String, activity: Activity, fragment:Fragment): PermissionService? {
        when(permissionName){
            Permissions.LOCATION.permName -> return LocationPermission(activity, fragment)
        }
        return null
    }
}