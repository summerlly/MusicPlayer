package tech.summerly.quiet.commonlib.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * Created by summer on 17-12-19
 */

/**
 * observe forever but filter null values
 * TODO add a destroy method
 */
fun <T : Any> LiveData<T>.observeForeverFilterNull(observer: (T) -> Unit) {
    observeForever { it?.let(observer) }
}

fun <T : Any> LiveData<T>.observeFilterNull(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
    observe(lifecycleOwner, Observer<T> {
        it?.let(observer)
    })
}

fun <T : Any> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, observer: (T?) -> Unit) {
    observe(lifecycleOwner, Observer<T> {
        observer(it)
    })
}