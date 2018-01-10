package tech.summerly.quiet.local.fragments

import tech.summerly.quiet.local.LocalMusicApi
import tech.summerly.quiet.local.database.database.Table


/**
 * Created by summer on 17-12-23
 */
class LocalTotalFragment : BaseLocalFragment() {
    override fun isInterestedChange(table: Table): Boolean {
        return table == Table.Music
    }


    override suspend fun loadData(localMusicApi: LocalMusicApi): List<Any> {
        return localMusicApi.getTotalMusics()
    }
}