package org.techtown.nanez.utils.util

import android.os.Bundle
import android.os.Looper
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Created by iseungjun on 2023/08/17
 */

fun <T> MutableLiveData<T>.post(event: T?) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        this.value = event
    } else {
        this.postValue(event)
    }
}


@MainThread
inline fun <T> LiveData<Event<T>>.eventObserve(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { t ->
        t.getContentIfNotHandled()?.let {
            onChanged.invoke(it)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

fun <T : Fragment> AppCompatActivity.addFragment(container: ViewGroup, saveInstanceState: Bundle? = null, tag: String, arguments: Bundle? = null, isNeedBackStack: Boolean = false, newFragment: (() -> T?)?): T? {
    var fragment: T? = null

    saveInstanceState?.let {
        fragment = supportFragmentManager.findFragmentByTag(tag) as? T
    }

    fragment?.let { frag ->
        arguments?.let { arg ->
            if (frag.arguments != null) {
                frag.arguments?.let { existBundle ->
                    existBundle.clear()
                    arg.let { existBundle.putAll(arg) }
                }
            } else {
                frag.arguments = arguments
            }
        }

        if (frag.isAdded) {
            return fragment
        }

    } ?: run {
        fragment = newFragment?.invoke()
        arguments?.let {
            fragment?.arguments = it
        }
    }
    fragment?.let {
        if (isNeedBackStack) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(tag)
                .replace(container.id, it, tag)
                .commitAllowingStateLoss()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(container.id, it, tag)
                .commitAllowingStateLoss()
        }
    }
    return fragment
}
