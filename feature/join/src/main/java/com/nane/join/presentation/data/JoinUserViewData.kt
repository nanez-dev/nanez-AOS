package com.nane.join.presentation.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by haul on 11/16/23
 */
@Parcelize
data class JoinUserViewData(
    var nickName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var referCode: String? = null,
    var accordId: Int = -1,
    var isAccepted: Boolean = false,
) : Parcelable
