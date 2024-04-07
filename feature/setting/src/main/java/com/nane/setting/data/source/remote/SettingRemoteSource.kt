package com.nane.setting.data.source.remote

import com.nane.network.api.users.WithdrawalApi
import com.nane.network.service.RetrofitUserService
import com.nane.setting.data.source.ISettingRemoteSource
import retrofit2.Response
import javax.inject.Inject

class SettingRemoteSource @Inject constructor(
    private val userService: RetrofitUserService
): ISettingRemoteSource {

    override suspend fun withdraw()= userService.deleteWithdraw()
}