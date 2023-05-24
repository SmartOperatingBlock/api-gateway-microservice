/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity

import entity.room.Room
import entity.room.RoomData
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe

class TestRoom : StringSpec({

    "A room shouldn't have an empty room id" {
        shouldThrow<IllegalArgumentException> {
            Room(
                RoomData.RoomId(""),
                "name",
                RoomData.ZoneId("zone4"),
                RoomData.RoomType.PRE_OPERATING_ROOM,
            )
        }
    }

    "A room shouldn't have an empty zone id" {
        shouldThrow<IllegalArgumentException> {
            Room(
                RoomData.RoomId("room4"),
                "name",
                RoomData.ZoneId(""),
                RoomData.RoomType.PRE_OPERATING_ROOM,
            )
        }
    }

    "It should be possible to create a room" {
        Room(
            RoomData.RoomId("room4"),
            "name",
            RoomData.ZoneId("zone4"),
            RoomData.RoomType.PRE_OPERATING_ROOM,
        ) shouldNotBe null
    }
})
