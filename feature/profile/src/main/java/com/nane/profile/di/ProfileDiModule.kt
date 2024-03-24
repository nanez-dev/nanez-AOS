package com.nane.profile.di

import com.nane.profile.data.repo.IProfileRepository
import com.nane.profile.data.repo.impl.ProfileRepositoryImpl
import com.nane.profile.data.source.IProfileLocalSource
import com.nane.profile.data.source.IProfileRemoteSource
import com.nane.profile.data.source.impl.ProfileLocalSourceImpl
import com.nane.profile.data.source.impl.ProfileRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by haul on 3/2/24
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class ProfileDiModule {

    @Binds
    @ViewModelScoped
    abstract fun bindProfileRemoteSource(impl: ProfileRemoteSourceImpl): IProfileRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): IProfileRepository

    @Binds
    @ViewModelScoped
    abstract fun bindProfileLocalSource(impl: ProfileLocalSourceImpl): IProfileLocalSource
}