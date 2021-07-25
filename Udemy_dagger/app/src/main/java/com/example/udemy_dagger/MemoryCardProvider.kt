package com.example.udemy_dagger

import dagger.Module
import dagger.Provides

@Module
//module can have more than one provider functions
class MemoryCardProvider {
    @Provides   // this annotation is necessary for dagger to recognise it as the provider
    fun ProvidesMemoryCard():MemoryCard{
        return MemoryCard()
    }
}


//module should not have states- instant variables , this may lead to unpredictable behaviour