/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api

import entity.room.Room
import entity.room.RoomData

/**
 * The model of the Room DTO composed by [id], [name],
 * [zoneId] and the [type].
 */
data class RoomDto(
    val id: String,
    val name: String,
    val zoneId: String,
    val type: String,
)

/**
 * The Dto for the [numberOfZones] inside the Operating block.
 */
data class NumberOfZonesDto(val numberOfZones: Int)

/** Extension function to convert a [Room] to its [RoomDto]. */
fun Room.toRoomDto() =
    RoomDto(
        this.id.id,
        this.name,
        this.zoneId.id,
        this.type.toString(),
    )

/** Extension function to convert a [RoomDto] to its [Room]. */
fun RoomDto.toRoom() =
    Room(
        RoomData.RoomId(this.id),
        this.name,
        RoomData.ZoneId(this.id),
        RoomData.RoomType.valueOf(this.type),
    )
