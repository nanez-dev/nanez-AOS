package com.nane.theme.di

import com.nane.theme.data.repository.ThemeRepositoryImpl
import com.nane.theme.data.source.IThemeRemoteSource
import com.nane.theme.data.source.remote.ThemeRemoteSourceImpl
import com.nane.theme.domain.repository.IThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class ThemeDiModule {

    @Binds
    @ViewModelScoped
    abstract fun bindThemeRemoteSource(impl: ThemeRemoteSourceImpl): IThemeRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindThemeRepository(impl: ThemeRepositoryImpl): IThemeRepository
}