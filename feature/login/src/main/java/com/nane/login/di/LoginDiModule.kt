package com.nane.login.di

import com.nane.login.data.repository.IUserRepository
import com.nane.login.data.repository.impl.UserRepository
import com.nane.login.data.source.IUserLocalDataSource
import com.nane.login.data.source.IUserRemoteDataSource
import com.nane.login.data.source.local.UserLocalDataSource
import com.nane.login.data.source.remote.UserRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by iseungjun on 2023/10/06
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginDiModule {

    @Binds
    @ViewModelScoped
    abstract fun bindUserLocalDataSource(impl: UserLocalDataSource): IUserLocalDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindUserRemoteSource(impl: UserRemoteDataSource): IUserRemoteDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindUserRepository(impl: UserRepository): IUserRepository
}