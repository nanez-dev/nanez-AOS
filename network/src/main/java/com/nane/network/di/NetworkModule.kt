package com.nane.network.di

import com.nane.network.service.RetrofitAccordService
import com.nane.network.service.RetrofitBrandService
import com.nane.network.service.RetrofitPerfumeService
import com.nane.network.service.RetrofitSearchService
import com.nane.network.service.RetrofitUserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by iseungjun on 2023/08/21
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): RetrofitUserService {
        return retrofit.create(RetrofitUserService::class.java)
    }

    @Provides
    @Singleton
    fun provideAccordService(retrofit: Retrofit): RetrofitAccordService {
        return retrofit.create(RetrofitAccordService::class.java)
    }

    @Provides
    @Singleton
    fun provideBrandService(retrofit: Retrofit): RetrofitBrandService {
        return retrofit.create(RetrofitBrandService::class.java)
    }

    @Provides
    @Singleton
    fun providePerfumeService(retrofit: Retrofit): RetrofitPerfumeService {
        return retrofit.create(RetrofitPerfumeService::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchService(retrofit: Retrofit): RetrofitSearchService {
        return retrofit.create(RetrofitSearchService::class.java)
    }
}