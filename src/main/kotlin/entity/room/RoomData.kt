/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.room

/**
 * Module with all data necessary for [Room] entity.
 */
object RoomData {

    /** The [id] of the [Room]. */
    data class RoomId(val id: String)

    /** The [id] of the zone. */
    data class ZoneId(val id: String)

    /** The [Room] type. */
    enum class RoomType {
        PRE_OPERATING_ROOM, OPERATING_ROOM
    }
}
