/*
 * Copyright (C) 2017  YangBin
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package tech.summerly.quiet.commonlib.bean

import android.os.Parcelable

/**
 *
 */
interface MusicUri : Parcelable {

    val bitrate: Int

    val uri: String

    /**
     * Based on this variable, the player determines if the song is playable.
     *
     * When [dateValid] is greater than the currentTimeMillis, you can think
     * of this link [uri] has been invalidated
     */
    val dateValid: Long

    fun isValid() = dateValid > System.currentTimeMillis()
}