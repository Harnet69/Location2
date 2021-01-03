package com.harnet.location.model

import com.harnet.location.view.MapsFragment
import com.harnet.location.viewModel.MapsViewModel
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface MyLocationComponent {
    fun getMyLocation(): MyLocation

    fun inject(mapsViewModel: MapsViewModel)
}