package io.github.shvmsaini.nextdns.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.shvmsaini.nextdns.service.ApiService

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideDenylistService(): ApiService = ApiService.create()
}
