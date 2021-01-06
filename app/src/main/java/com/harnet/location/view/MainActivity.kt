package com.harnet.location.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.harnet.location.R
import com.harnet.location.model.AppPermissions
import com.harnet.location.model.Permissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {    // permission service
    lateinit var appPermissions: AppPermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appPermissions = AppPermissions()
    }

    // when user was asked for a permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissions.isNotEmpty()) {
            //switcher of different kinds of permissions
            when (permissions[0]) {
                android.Manifest.permission.ACCESS_FINE_LOCATION -> {
                    when (fragment.childFragmentManager.primaryNavigationFragment) {
                        // request the appropriate fragment
                        is MapsFragment ->
                            appPermissions.getPermissionClass(Permissions.LOCATION.permissionName, this, fragment)
                                ?.onRequestPermissionsResult(
                                    requestCode,
                                    permissions,
                                    grantResults
                                )
                    }
                }
            }
        }
    }
}