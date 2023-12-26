package com.nane.join.di

import com.nane.join.data.repo.impl.JoinRepositoryImpl
import com.nane.join.data.source.IJoinLocalSource
import com.nane.join.data.source.IJoinRemoteSource
import com.nane.join.data.source.impl.JoinLocalSourceImpl
import com.nane.join.data.source.impl.JoinRemoteSourceImpl
import com.nane.join.domain.repo.IJoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by haul on 10/30/23
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class JoinDataModule {

    @Binds
    @ViewModelScoped
    abstract fun bindJoinRepository(impl: JoinRepositoryImpl): IJoinRepository

    @Binds
    @ViewModelScoped
    abstract fun bindJoinRemoteSource(impl: JoinRemoteSourceImpl): IJoinRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindJoinLocalSource(impl: JoinLocalSourceImpl): IJoinLocalSource
}