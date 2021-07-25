package com.example.udemy_dagger

import dagger.Component
import dagger.Module

@Component(modules=[MemoryCardProvider::class, NCBatteryModule::class])
interface SmartPhoneComponent{
    fun getSmartPhone():SmartPhone
}