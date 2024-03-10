package com.nane.detail.di

import com.nane.detail.data.repo.IDetailRepository
import com.nane.detail.data.repo.impl.DetailRepositoryImpl
import com.nane.detail.data.source.IDetailRemoteSource
import com.nane.detail.data.source.impl.DetailRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by haul on 3/10/24
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class DetailDataModule {

    @Binds
    @ViewModelScoped
    abstract fun bindDetailRemoteSource(impl: DetailRemoteSourceImpl): IDetailRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindDetailRepository(impl: DetailRepositoryImpl): IDetailRepository
}