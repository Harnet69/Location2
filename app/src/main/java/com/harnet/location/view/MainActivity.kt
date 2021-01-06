package com.harnet.location.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.harnet.location.R
import com.harnet.location.model.AppPermissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {    // permission service
    lateinit var appPermissions: AppPermissions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appPermissions = AppPermissions(this, fragment)
    }

    // when user was asked for a permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //switcher of different kinds of permissions
        if (permissions.isNotEmpty()) {
            when (permissions[0]) {
                android.Manifest.permission.ACCESS_FINE_LOCATION -> {
                    when (val activeFragment: Fragment? =
                        fragment.childFragmentManager.primaryNavigationFragment) {
                        is MapsFragment ->
                            appPermissions.locationPermission.onRequestPermissionsResult(
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