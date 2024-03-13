package org.techtown.nanez.utils.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import org.techtown.nanez.utils.NaneLog
import kotlin.math.roundToInt

/**
 * Created by iseungjun on 2023/08/19
 */
class ResUtils {
    private object LazyHolder {
        val INSTANCE = ResUtils()
    }

    companion object {
        @JvmStatic
        val instance by lazy { LazyHolder.INSTANCE }

        val displayMetrics: DisplayMetrics get() = Resources.getSystem().displayMetrics

        @JvmStatic
        fun init(context: Context) {
            if (context is Application) {
                this.instance.setContext(context)
            } else {
                throw RuntimeException("only initialize application")
            }
        }

        @JvmStatic
        fun getDrawable(context: Context?, @DrawableRes drawableResId: Int): Drawable? {
            return try {
                context?.getDrawable(drawableResId)
            } catch (e: Resources.NotFoundException) {
                NaneLog.e(e)
                return null
            }
        }

        @JvmStatic
        fun getColor(context: Context?, @ColorRes colorRes: Int): Int {
            return try {
                context?.let {
                    ContextCompat.getColor(it, colorRes) //context.getColor 가 api level 23 에서추가됨.
                } ?: 0
            } catch (e: Resources.NotFoundException) {
                NaneLog.e(e)
                0
            }
        }

        @JvmStatic
        fun getDp(context: Context?, @DimenRes res: Int): Int {
            return context?.resources?.getDimension(res)?.dpToPx()?.roundToInt() ?: 0
        }
    }

    private lateinit var applicationContext: Context

    private fun setContext(applicationContext: Context) {
        this.applicationContext = applicationContext
    }

    fun getPackageManager(): PackageManager {
        return applicationContext.packageManager
    }

    fun getActivityManager(): ActivityManager {
        return applicationContext.getSystemService(Activity.ACTIVITY_SERVICE) as ActivityManager
    }

    @Throws(PackageManager.NameNotFoundException::class, RuntimeException::class)
    fun getPackageInfo(packageName: String? = getPackageName()): PackageInfo? {
        return packageName?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                getPackageManager().getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
            } else {
                getPackageManager().getPackageInfo(packageName, 0)
            }
        }
    }

    @Throws(PackageManager.NameNotFoundException::class, RuntimeException::class)
    fun getApplicationInfo(packageName: String? = getPackageName()): ApplicationInfo? {
        return packageName?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                getPackageManager().getApplicationInfo(packageName, PackageManager.ApplicationInfoFlags.of(0))
            } else {
                getPackageManager().getApplicationInfo(packageName, 0)
            }
        }
    }

    fun getPackageName(): String? {
        return runCatching { applicationContext.packageName }.getOrNull()
    }

    fun getContentResolver(): ContentResolver? {
        return applicationContext.contentResolver
    }

    fun getColor(@ColorRes res: Int): Int {
        return applicationContext.getColor(res)
    }

    fun getString(@StringRes resId: Int): String {
        return applicationContext.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return applicationContext.getString(resId, *formatArgs)
    }

    @SuppressLint("ResourceType")
    fun getStringArray(@StringRes resId: Int): Array<String> {
        return applicationContext.resources.getStringArray(resId)
    }
}

fun Fragment.getString(@StringRes res: Int) = context?.getString(res)
fun View.getString(@StringRes res: Int) = context?.getString(res)
fun Fragment.getString(@StringRes resId: Int, vararg formatArgs: Any) = context?.getString(resId, *formatArgs)
fun View.getString(@StringRes resId: Int, vararg formatArgs: Any) = context?.getString(resId, *formatArgs)

fun Context.getDp(@DimenRes res: Int) = ResUtils.getDp(this, res)
fun Fragment.getDp(@DimenRes res: Int) = ResUtils.getDp(context, res)
fun View.getDp(@DimenRes res: Int) = ResUtils.getDp(context, res)

fun Fragment.getColor(@ColorRes res: Int) = ResUtils.getColor(context, res)
fun View.getColor(@ColorRes res: Int) = ResUtils.getColor(context, res)

fun Fragment.getDrawableRes(@DrawableRes res: Int): Drawable? = ResUtils.getDrawable(context, res)
fun View.getDrawableRes(@DrawableRes res: Int): Drawable? = ResUtils.getDrawable(context, res)
fun Context.getDrawableRes(@DrawableRes res: Int): Drawable? = ResUtils.getDrawable(this, res)
fun Context.getVector(@DrawableRes res: Int): Drawable? = ResUtils.getDrawable(this, res)


fun Number.dpToPx(): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, this.toFloat(), ResUtils.displayMetrics)
fun Number.toDp(): Int = toDpf().toInt()
fun Number.toDpf(): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), ResUtils.displayMetrics)
