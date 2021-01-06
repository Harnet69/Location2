package com.harnet.location.model.di

import com.harnet.location.model.AppPermissions
import com.harnet.location.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface AppPermissionsComponent {

    fun getAppPermissions(): AppPermissions

    fun inject(mainActivity: MainActivity)
}