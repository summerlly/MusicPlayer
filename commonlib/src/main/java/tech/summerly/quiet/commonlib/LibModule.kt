package tech.summerly.quiet.commonlib

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.stetho.Stetho
import tech.summerly.quiet.commonlib.player.MusicPlayerManager

/**
 * Created by summer on 17-12-17.
 * Base Application context
 */
class LibModule {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        val instance: Context
            get() = context!!
    }

    fun onCreate(context: Context) {
        LibModule.context = context
        MusicPlayerManager.init(context)
        Stetho.initializeWithDefaults(context)
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(context.applicationContext as Application?)
    }
}