package com.example.trackfit.model.service.module

import com.example.trackfit.model.service.AccountService
import com.example.trackfit.model.service.LogService
import com.example.trackfit.model.service.StorageService
import com.example.trackfit.model.service.impl.AccountServiceImpl
import com.example.trackfit.model.service.impl.LogServiceImpl
import com.example.trackfit.model.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}