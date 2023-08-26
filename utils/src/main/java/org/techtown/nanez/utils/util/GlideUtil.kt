package org.techtown.nanez.utils.util

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class GlideUtil {
    private object LazyHolder {
        val INSTANCE = GlideUtil()
    }

    companion object {
        @JvmStatic
        val instance by lazy { LazyHolder.INSTANCE }

        @JvmStatic
        fun clearMemory(context: Context?) {
            context?.let { Glide.get(context).clearMemory() }
        }
    }

    interface GlideDrawableLoadReadyCallBack {
        fun onLoadFailed()
        fun onLoadSuccess()
    }


    fun clearView(imageView: ImageView?) {
        imageView?.let {
            if (checkContextState(it.context)) {
                Glide.with(it.context).clear(imageView)
            }
        }
    }

    /**
     * 일반적인 이미지뷰에 쓰이는 함수
     * @param data 이미지뷰에 셋팅될 glide data
     */
    fun displayImage(data: GlideImageLoadData) {
        if (!checkContextState(data.imageView.context)) {
            return
        }

        val options = RequestOptions()
        data.run {
            optionData?.let { etcOptionData ->
                setDefaultOption(options, etcOptionData, imageView.context)
            }

            clearView(imageView)
            Glide.with(imageView.context).load(imageUrl).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    drawableCallBack?.onLoadFailed()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    drawableCallBack?.onLoadSuccess()
                    return false
                }
            }).apply(options).into(imageView)
        }
    }



    private fun setDefaultOption(options: RequestOptions, etcOptionData: GlideEtcOptionData, context: Context) {
        if (etcOptionData.placeHolderId > 0) {
            options.placeholder(ResUtils.getDrawable(context, etcOptionData.placeHolderId))
        } else {
            if (etcOptionData.placeHolderDrawable != null) {
                options.placeholder(etcOptionData.placeHolderDrawable)
            }
        }

        if (etcOptionData.reqWidth > 0 && etcOptionData.reqHeight > 0) {
            options.override(etcOptionData.reqWidth, etcOptionData.reqHeight)
        }

        if (!etcOptionData.skipMemoryCache) {
            options.diskCacheStrategy(etcOptionData.cacheOptionType?.type ?: GlideImageCacheOptionType.USE_RESOURCE_CACHE.type)
        } else {
            options.diskCacheStrategy(GlideImageCacheOptionType.NO_CACHE.type)
        }

        options.skipMemoryCache(etcOptionData.skipMemoryCache)
        setImageOption(options, etcOptionData)
    }


    private fun setImageOption(options: RequestOptions, etcOptionData: GlideEtcOptionData) {
        val transformation = arrayListOf<Transformation<Bitmap>>()
        etcOptionData.imageOptionType?.forEach { type: GlideImageOptionType ->
            when (type) {
                GlideImageOptionType.CIRCLE -> {
                    transformation.add(CircleCrop())
                }

                GlideImageOptionType.BLUR -> {
                    transformation.add(BlurTransformation(etcOptionData.blur.toDp()))
                }

                GlideImageOptionType.ROUND -> {
                    if (etcOptionData.round > 0) {
                        transformation.add(RoundedCornersTransformation(etcOptionData.round.toDp(), 0, etcOptionData.roundCornerOption))
                    }
                }

                GlideImageOptionType.CENTER_CROP -> {
                    transformation.add(0, CenterCrop())
                }

                GlideImageOptionType.CENTER_INSIDE -> {
                    transformation.add(0, CenterInside())
                }

                GlideImageOptionType.FIT_CENTER -> {
                    transformation.add(0, FitCenter())
                }

                GlideImageOptionType.NO_ANIM -> {
                    options.dontAnimate()
                }

                GlideImageOptionType.HIGH_QUALITY -> {
                    options.format(DecodeFormat.PREFER_ARGB_8888)
                }
                else -> {}
            }
        }

        if (transformation.isNotEmpty()) {
            options.transform(MultiTransformation(transformation))
        }
    }


    private fun checkContextState(context: Context?): Boolean {
        context?.let {
            val act = it as? Activity
            if (act != null && (act.isDestroyed || act.isFinishing)) {
                return false
            }

            return true
        }
        return false
    }
}