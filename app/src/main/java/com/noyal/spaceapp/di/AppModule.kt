package com.noyal.spaceapp.di

import android.app.Application
import com.noyal.spaceapp.util.getJsonDataFromAsset
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsData(application: Application) = application.getJsonDataFromAsset("data.json")
}