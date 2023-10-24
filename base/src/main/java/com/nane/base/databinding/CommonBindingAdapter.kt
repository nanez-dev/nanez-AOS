package com.nane.base.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import org.techtown.nanez.utils.util.GlideEtcOptionData
import org.techtown.nanez.utils.util.GlideImageLoadData
import org.techtown.nanez.utils.util.GlideImageOptionType
import org.techtown.nanez.utils.util.GlideUtil

/**
 * Created by iseungjun on 2023/08/26
 */
object CommonBindingAdapter {

    @JvmStatic
    @BindingAdapter("img_load", "img_round", requireAll = false)
    fun ImageView.setLoadImage(url: String?, roundInt: Int) {
        clipToOutline = true
        GlideUtil.instance.displayImage(GlideImageLoadData(this, url,
            optionData = GlideEtcOptionData().apply {
                imageOptionType = listOf(GlideImageOptionType.FIT_CENTER, GlideImageOptionType.ROUND)
                round = roundInt
            })
        )
    }
}