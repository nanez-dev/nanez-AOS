package com.nane.search.di

import com.nane.search.data.repository.SearchRepositoryImpl
import com.nane.search.data.source.ISearchRemoteSource
import com.nane.search.data.source.remote.SearchRemoteSourceImpl
import com.nane.search.domain.repository.ISearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchDiModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSearchRemoteSource(impl: SearchRemoteSourceImpl): ISearchRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindSearchRepository(impl: SearchRepositoryImpl): ISearchRepository
}