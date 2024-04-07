package com.nane.setting.di

import com.nane.setting.data.repository.SettingRepositoryImpl
import com.nane.setting.data.source.ISettingRemoteSource
import com.nane.setting.data.source.remote.SettingRemoteSource
import com.nane.setting.domain.repository.ISettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class SettingDiModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSettingRemoteSource(impl: SettingRemoteSource): ISettingRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindSettingRepository(impl: SettingRepositoryImpl): ISettingRepository
}