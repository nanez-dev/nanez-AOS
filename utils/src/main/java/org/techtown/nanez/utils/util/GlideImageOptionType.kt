package org.techtown.nanez.utils.util

import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by haul on 2021/10/29
 */
enum class GlideImageOptionType {
    NORMAL, ROUND, BLUR, CIRCLE,
    CENTER_CROP, FIT_CENTER, CENTER_INSIDE,
    NO_ANIM,
    HIGH_QUALITY
}

/**
 * 이미지 캐싱 옵션
 *
 * @property type
 * NO_CACHE 캐싱 안함
 * USE_ALL_CACHE 리소스 및 데이터 캐싱 둘 다
 * USE_RESOURCE_CACHE 해상도 조정 후 이미지 캐싱 (기본값)
 * USE_AUTO_CACHE 알아서 캐싱
 * USE_DATA_CACHE 원본 해상도 그대로 이미지 캐싱
 */
enum class GlideImageCacheOptionType(val type: DiskCacheStrategy) {
    NO_CACHE(DiskCacheStrategy.NONE),
    USE_ALL_CACHE(DiskCacheStrategy.ALL),
    USE_RESOURCE_CACHE(DiskCacheStrategy.RESOURCE),
    USE_AUTO_CACHE(DiskCacheStrategy.AUTOMATIC),
    USE_DATA_CACHE(DiskCacheStrategy.DATA)
}