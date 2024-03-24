package com.nane.base.view.dialog.data

import androidx.annotation.ColorRes

/**
 * Created by haul on 3/24/24
 */
data class DialogBuildData(
    val title: String?,
    val content: String? = null,
    val negativeText: String? = null,
    val positiveText: String? = null,
    val onNegativeAction: (() -> Unit)? = null,
    val onPositiveAction: (() -> Unit)? = null,
    val onDisMiss: (() -> Unit)? = null,
    @ColorRes
    val titleTextColor: Int = -1,
    @ColorRes
    val negativeBtnTextColor: Int = -1,
    @ColorRes
    val positiveTextColor: Int = -1,
)
