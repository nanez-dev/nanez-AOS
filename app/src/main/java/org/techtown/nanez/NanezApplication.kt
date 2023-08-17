package org.techtown.nanez

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.techtown.nanez.utils.NanezDataStore

/**
 * Created by iseungjun on 2023/08/17
 */
@HiltAndroidApp
class NanezApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        NanezDataStore.getInstance().init(this)
    }
}