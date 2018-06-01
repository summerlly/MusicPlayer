package tech.summerly.quiet.commonlib

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.facebook.stetho.Stetho
import tech.summerly.quiet.commonlib.base.BaseModule
import tech.summerly.quiet.commonlib.component.activities.AppTask
import tech.summerly.streamcache.CacheGlobalSetting
import java.io.File

/**
 * Created by summer on 17-12-17.
 * Base Application context
 */
internal object LibModule : BaseModule() {


    override fun onCreate() {

        Stetho.initializeWithDefaults(this)
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(applicationContext as Application?)

        CacheGlobalSetting.CACHE_PATH = File(externalCacheDir, "musics_cache").path
        (baseContext as Application).registerActivityLifecycleCallbacks(AppTask.CallBack)
    }

}
