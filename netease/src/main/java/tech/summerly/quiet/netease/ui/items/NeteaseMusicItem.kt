@file:Suppress("NOTHING_TO_INLINE")

package tech.summerly.quiet.netease.ui.items

import android.support.v7.widget.PopupMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.netease_item_music.view.*
import tech.summerly.quiet.commonlib.bean.Music
import tech.summerly.quiet.commonlib.utils.ItemViewBinder
import tech.summerly.quiet.commonlib.utils.gone
import tech.summerly.quiet.commonlib.utils.popupMenu
import tech.summerly.quiet.commonlib.utils.visible
import tech.summerly.quiet.netease.R

/**
 * author : yangbin10
 * date   : 2018/1/15
 */
open class NeteaseMusicItemViewBinder : ItemViewBinder<Music>() {
    override fun onBindViewHolder(holder: ViewHolder, item: Music): Unit = with(holder.itemView) {
        checkMv(item)
        checkQuality(item)
        textTitle.text = item.title
        textSubTitle.text = item.artistAlbumString()
        imageMore.setOnClickListener {
            val menu = popupMenu(it, R.menu.netease_popup_music_item) {
                onMorePopupMenuClick(it, item)
                true
            }
            onMorePopupMenuShow(menu)
        }
        requestLayout()
    }

    protected open fun onMorePopupMenuShow(menu: PopupMenu) {

    }

    protected open fun onMorePopupMenuClick(item: MenuItem, music: Music) {

    }

    private inline fun View.checkQuality(music: Music) {
        val quality = music.getHighestQuality()
        if (quality.isNullOrEmpty()) {
            indicatorQuality.gone()
        } else {
            indicatorQuality.visible()
            indicatorQuality.text = quality
        }
    }

    /**
     * check mv availability
     */
    private inline fun View.checkMv(music: Music) {
        if (music.mvId == 0L) {
            indicatorMV.gone()
            indicatorMV.setOnClickListener(null)
        } else {
            indicatorMV.visible()
            indicatorMV.setOnClickListener {
                //todo
            }
        }
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(R.layout.netease_item_music, parent, inflater)
    }

}