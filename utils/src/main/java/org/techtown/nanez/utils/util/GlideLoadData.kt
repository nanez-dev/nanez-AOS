package org.techtown.nanez.utils.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Created by haul on 2021/10/29
 */

/**
 * 일반적으로 그냥 이미지뷰에 이미지 로드하는 glide 데이터
 *
 * @param imageView 이미지뷰
 * @param imageUrl 로드할 이미지
 * @param optionData 기타 옵션
 * @param drawableCallBack 이미지뷰에 셋팅 후 콜백 받아서 무언가 처리해야하는 경우 쓰이는 리스너
 */
data class GlideImageLoadData(
    val imageView: ImageView,
    val imageUrl: String? = "",
    val optionData: GlideEtcOptionData? = null,
    val drawableCallBack: GlideUtil.GlideDrawableLoadReadyCallBack? = null
)


// 기타 옵션 데이터 클래스
class GlideEtcOptionData {
    var imageOptionType: List<GlideImageOptionType>? = listOf(GlideImageOptionType.NORMAL)
    var cacheOptionType: GlideImageCacheOptionType? = GlideImageCacheOptionType.USE_RESOURCE_CACHE

    // 메모리 캐싱 할지 말지 여부 이게 false면 디스크캐싱도 안함
    var skipMemoryCache = false
    var clearCacheData = false

    @DrawableRes
    var placeHolderId: Int = -1
    var placeHolderDrawable: Drawable? = null

    var reqWidth: Int = -1
    var reqHeight: Int = -1

    // imageOptionType에 GlideImageOptionType.ROUND 가 추가된 경우 변경필요
    var round: Int = -1
    var roundCornerOption: RoundedCornersTransformation.CornerType? = RoundedCornersTransformation.CornerType.ALL

    // imageOptionType에 GlideImageOptionType.BLUR 가 추가된 경우 변경필요
    var blur: Int = -1
}
