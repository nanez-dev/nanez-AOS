package com.nane.home.di

import com.nane.home.data.repository.HomeRepositoryImpl
import com.nane.home.data.source.IHomeRemoteSource
import com.nane.home.data.source.remote.HomeRemoteSourceImpl
import com.nane.home.domain.repository.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by iseungjun on 2023/09/11
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeDiModule {

    @Binds
    @ViewModelScoped
    abstract fun bindHomeRemoteSource(impl: HomeRemoteSourceImpl): IHomeRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): IHomeRepository
}