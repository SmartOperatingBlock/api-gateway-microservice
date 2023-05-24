/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.serialization

import application.presenter.api.report.measurements.ValueWithUnit
import application.presenter.api.report.room.RoomApiDto
import application.presenter.api.report.room.RoomApiDtoType
import application.presenter.api.report.room.RoomEnvironmentalDataApiDto
import entity.surgeryreport.room.Room
import entity.surgeryreport.room.RoomEnvironmentalData
import entity.surgeryreport.room.RoomType

/**
 * Serializers for data to return to API.
 */
object RoomSerializer {
    /**
     * Extension method to obtain the api dto of a room.
     */
    fun Room.toApiDto(): RoomApiDto = RoomApiDto(
        id = this.id.value,
        type = this.type.toApiDtoType(),
    )

    /**
     * Extension method to obtain the api dto type of a room.
     */
    fun RoomType.toApiDtoType() = when (this) {
        RoomType.OPERATING_ROOM -> RoomApiDtoType.OPERATING_ROOM
        RoomType.PRE_OPERATING_ROOM -> RoomApiDtoType.PRE_OPERATING_ROOM
    }

    /**
     * Extension method to obtain the api dto of room environmental data.
     */
    fun RoomEnvironmentalData.toApiDto() = RoomEnvironmentalDataApiDto(
        temperature = this.temperature?.let { ValueWithUnit(it.value, it.unit.toString()) },
        humidity = this.humidity?.percentage?.value,
        luminosity = this.luminosity?.let { ValueWithUnit(it.value, it.unit.toString()) },
        presence = this.presence?.presenceDetected,
    )
}
