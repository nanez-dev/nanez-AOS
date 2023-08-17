package org.techtown.nanez.domain.mapper

import org.techtown.nanez.data.data.UserLoginDTO
import org.techtown.nanez.domain.data.UserLoginDomainDTO
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/17
 */
class UserDomainMapper @Inject constructor(){

    fun toDomainDTO(dto: UserLoginDTO): UserLoginDomainDTO {
        return UserLoginDomainDTO(
            email = dto.userEmail,
            passWord = dto.userPassword
        )
    }

}