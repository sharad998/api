package com.example.udemy_dagger

import dagger.Module
import dagger.Provides

@Module
class NCBatteryModule {

    @Provides
    fun provideNCBattery(nickelCadmiumBattery: NickelCadmiumBattery):Battery{
        return nickelCadmiumBattery

        //return NickelCadmiumBattery()

    }
}