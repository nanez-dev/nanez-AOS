package org.techtown.nanez.common

import androidx.lifecycle.Observer

open class Event<out T>(private val content: T) {

    private val map by lazy { hashMapOf<Int, Boolean>() }

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(key: Int = 0): T? {
        val hasBeenHandled = map.getOrElse(key) {
            map[key] = false
            false
        }
        return if (hasBeenHandled) {
            null
        } else {
            map[key] = true
            this.hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}


/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>) {
        event.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}