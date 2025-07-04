package com.example.kakaomappath.di

import com.example.kakaomappath.api.map.remote.repository.MapRepository
import com.example.kakaomappath.api.map.remote.repository.MapRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsMapRepository(
        mapRepositoryImpl: MapRepositoryImpl
    ): MapRepository
}