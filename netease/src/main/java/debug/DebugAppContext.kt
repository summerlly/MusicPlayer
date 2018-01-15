package debug

import android.app.Application
import tech.summerly.quiet.commonlib.LibModule
import tech.summerly.quiet.netease.NeteaseModule

/**
 * Created by summer on 18-1-14
 */
class DebugAppContext : Application() {

    override fun onCreate() {
        super.onCreate()
        LibModule().onCreate(this)
        NeteaseModule().onCreate(this)
    }
}