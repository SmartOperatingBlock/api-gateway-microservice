/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.room

import kotlinx.serialization.Serializable

/**
 * Module with all data necessary for [Room] entity.
 */
object RoomData {

    /** The [id] of the [Room]. */
    @Serializable
    data class RoomId(val id: String) {
        init {
            // Constructor validation: The id must not be empty
            require(this.id.isNotEmpty())
        }
    }

    /** The [id] of the zone. */
    data class ZoneId(val id: String) {
        init {
            // Constructor validation: The id must not be empty
            require(this.id.isNotEmpty())
        }
    }

    /** The [Room] type. */
    enum class RoomType {
        PRE_OPERATING_ROOM, OPERATING_ROOM
    }
}
