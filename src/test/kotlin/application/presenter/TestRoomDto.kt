/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter

import application.presenter.api.room.RoomSerialization.toRoom
import application.presenter.api.room.RoomSerialization.toRoomDto
import entity.room.Room
import entity.room.RoomData
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class TestRoomDto : StringSpec({

    val room = Room(
        RoomData.RoomId("room1"),
        "room1",
        RoomData.ZoneId("zone5"),
        RoomData.RoomType.PRE_OPERATING_ROOM,
    )

    "A room should be correctly converted to its dto" {
        val roomDto = room.toRoomDto()
        roomDto shouldNotBe null
        roomDto.id shouldBeEqual room.id.id
        roomDto.zoneId shouldBeEqual room.zoneId.id
        roomDto.type.toString() shouldBeEqual room.type.toString()
    }

    "A room dto should be correctly converted to its entity" {
        room.toRoomDto().toRoom() shouldBe room
    }
})
