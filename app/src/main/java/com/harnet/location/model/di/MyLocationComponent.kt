package com.harnet.location.model.di

import com.harnet.location.model.MyLocation
import com.harnet.location.viewModel.MapsViewModel
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface MyLocationComponent {
    fun getMyLocation(): MyLocation

    fun inject(mapsViewModel: MapsViewModel)
}