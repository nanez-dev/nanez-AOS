package com.nane.password.di

import com.nane.password.data.repo.IPasswordRepository
import com.nane.password.data.repo.impl.PasswordRepositoryImpl
import com.nane.password.data.source.IPasswordRemoteSource
import com.nane.password.data.source.impl.PasswordRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by haul on 3/25/24
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class PasswordDiModule {

    @Binds
    @ViewModelScoped
    abstract fun binPasswordRepository(impl: PasswordRepositoryImpl): IPasswordRepository

    @Binds
    @ViewModelScoped
    abstract fun bindPasswordRemoteSource(impl: PasswordRemoteSourceImpl): IPasswordRemoteSource
}