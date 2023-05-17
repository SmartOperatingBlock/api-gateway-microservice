/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.room

/**
 * The model of the Room entity composed by [id], [name],
 * [zoneId] and the [type].
 */
data class Room(
    val id: RoomData.RoomId,
    val name: String,
    val zoneId: RoomData.ZoneId,
    val type: RoomData.RoomType,
)
