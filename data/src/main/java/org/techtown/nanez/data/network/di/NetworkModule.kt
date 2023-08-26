package org.techtown.nanez.data.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.techtown.nanez.data.network.RetrofitModule
import org.techtown.nanez.data.network.service.RetrofitAccordService
import org.techtown.nanez.data.network.service.RetrofitUserService
import retrofit2.Retrofit

/**
 * Created by iseungjun on 2023/08/21
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideUserService(retrofit: Retrofit): RetrofitUserService {
        return retrofit.create(RetrofitUserService::class.java)
    }

    @Provides
    fun provideAccordService(retrofit: Retrofit): RetrofitAccordService {
        return retrofit.create(RetrofitAccordService::class.java)
    }
}