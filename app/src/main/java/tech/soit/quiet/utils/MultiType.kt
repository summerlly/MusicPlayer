package tech.soit.quiet.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import me.drakeet.multitype.ItemViewBinder
import me.drakeet.multitype.MultiTypeAdapter
import tech.soit.quiet.R
import tech.soit.quiet.utils.annotation.LayoutId


open class KViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

@Deprecated("use LayoutId")
annotation class TypeLayoutRes(@LayoutRes val value: Int)

abstract class KItemViewBinder<T> : ItemViewBinder<T, KViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): KViewHolder {
        val layoutRes = (this::class.annotations.firstOrNull { it is TypeLayoutRes } as TypeLayoutRes?)?.value
                ?: this::class.java.getAnnotation(LayoutId::class.java)?.value
                ?: throw IllegalStateException("must override this function if you do not use Annotation")

        val view = inflater.inflate(layoutRes, parent, false)
        onViewCreated(view)
        return KViewHolder(view)
    }

    /**
     * callback when inflate a new item view by annotation
     */
    protected open fun onViewCreated(view: View) {

    }

    /**
     * link with [androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup]
     *
     * 此 itemView 所占的 span大小
     *
     */
    open val spanSize: Int = 1

}


inline fun <reified T, V : RecyclerView.ViewHolder, B : ItemViewBinder<T, V>> MultiTypeAdapter.withBinder(
        binder: B): MultiTypeAdapter {
    register(T::class.java, binder)
    return this
}

fun MultiTypeAdapter.submit(items: List<Any>) {
    this.items = items
    notifyDataSetChanged()
}


/*
 * easy access for loading
 */

@LayoutId(R.layout.item_loading)
class LoadingViewBinder : KItemViewBinder<Loading>() {

    override fun onBindViewHolder(holder: KViewHolder, item: Loading) {
        //do nothing
    }
}

/**
 * object for [LoadingViewBinder]
 */
object Loading


/**
 * shortcut to register Loading
 */
fun MultiTypeAdapter.withLoadingBinder(): MultiTypeAdapter {
    return withBinder(LoadingViewBinder())
}


fun MultiTypeAdapter.setLoading() {
    items = listOf(Loading)
}

@LayoutId(R.layout.item_empty)
class EmptyViewBinder : KItemViewBinder<Empty>() {
    override fun onBindViewHolder(holder: KViewHolder, item: Empty) {
        //do nothing
    }

}


object Empty


fun MultiTypeAdapter.withEmptyBinder(): MultiTypeAdapter {
    return withBinder(EmptyViewBinder())
}

fun MultiTypeAdapter.setEmpty() {
    items = listOf(Empty)
}