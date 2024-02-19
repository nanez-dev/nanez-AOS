package com.nane.storage.di

import com.nane.storage.data.repository.IStorageRepository
import com.nane.storage.data.repository.Impl.StorageRepositoryImpl
import com.nane.storage.data.source.IStorageRemoteSource
import com.nane.storage.data.source.Impl.StorageRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by haul on 2/19/24
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class StorageDiModule {

    @Binds
    @ViewModelScoped
    abstract fun bindStorageRepository(impl: StorageRepositoryImpl): IStorageRepository

    @Binds
    @ViewModelScoped
    abstract fun bindStorageRemoteSource(impl: StorageRemoteSourceImpl): IStorageRemoteSource
}