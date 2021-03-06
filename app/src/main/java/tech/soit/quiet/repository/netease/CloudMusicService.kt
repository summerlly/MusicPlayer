@file:Suppress("SpellCheckingInspection")

package tech.soit.quiet.repository.netease

import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.http.*

/**
 * e-mail : yangbinyhbn@gmail.com
 * time   : 2017/8/22
 * desc   : 网易云音乐的API
 */
interface CloudMusicService {


    @FormUrlEncoded
    @POST("/weapi/search/get")
    fun searchMusic(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/v3/song/detail")
    fun musicDetail(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/song/enhance/player/url")
    fun musicUrl(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/song/lyric?os=osx&lv=-1&kv=-1&tv=-1")
    fun lyric(@Query("id") id: Long, @FieldMap request: Map<String, String>): Deferred<JsonObject>

    /**
     * login 不进行缓存
     */
    @Headers("Cache-Control: max-age=0")
    @FormUrlEncoded
    @POST("/weapi/login/cellphone")
    fun login(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/user/playlist")
    fun userPlayList(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/v1/discovery/recommend/songs")
    fun recommendSongs(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/point/dailyTask")
    fun dailySign(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/v1/user/detail/{id}")
    fun userDetail(@Path("id") id: Long, @FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/v1/discovery/recommend/resource")
    fun recommendPlaylist(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/personalized/mv")
    fun recommendMv(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/v3/playlist/detail")
    fun playlistDetail(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/mv/detail")
    fun mvDetail(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/v1/radio/get")
    fun personalFm(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/radio/like?alg=itembased&time=25")
    fun like(@Query("trackId") id: Long, @Query("like") like: Boolean, @FieldMap request: Map<String, String>): Deferred<JsonObject>

    @FormUrlEncoded
    @POST("/weapi/radio/trash/add?alg=RT&time=25")
    fun fmTrash(@Query("songId") id: Long, @FieldMap request: Map<String, String>): Deferred<JsonObject>

    /**
     * 获取用户信息 , 歌单，收藏，mv, dj 数量
     */
    @FormUrlEncoded
    @POST("/weapi/subcount")
    fun subcount(@FieldMap request: Map<String, String>): Deferred<JsonObject>


    /**
     * 所有榜单内容摘要
     */
    @FormUrlEncoded
    @POST("/weapi/toplist/detail")
    fun toplistDetail(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    /**
     * 推荐歌单
     */
    @FormUrlEncoded
    @POST("/weapi/personalized/playlist")
    fun personalizedPlaylist(@FieldMap request: Map<String, String>): Deferred<JsonObject>

    /**
     * 推荐新音乐
     */
    @FormUrlEncoded
    @POST("/weapi/personalized/newsong")
    fun personalizedNewSong(@FieldMap request: Map<String, String>): Deferred<JsonObject>


    /**
     * 主播电台
     */
    @FormUrlEncoded
    @POST("/weapi/personalized/djprogram")
    fun personalizedDj(@FieldMap request: Map<String, String>): Deferred<JsonObject>


    /**
     * 推荐MV
     */
    @FormUrlEncoded
    @POST("/weapi/personalized/mv")
    fun personalizedMv(@FieldMap request: Map<String, String>): Deferred<JsonObject>



}