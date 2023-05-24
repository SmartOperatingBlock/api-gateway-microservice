/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity

import entity.process.ProcessData
import entity.process.SurgicalProcess
import entity.room.RoomData
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe
import java.time.Instant

class TestSurgicalProcess : StringSpec({

    "It should be possible to create a Surgical Process" {
        SurgicalProcess(
            ProcessData.ProcessId("processId"),
            Instant.now(),
            "Colonscopy",
            ProcessData.PatientId("patient0"),
            ProcessData.HealthProfessionalId("hp-0"),
            ProcessData.RoomWithType(RoomData.RoomId("room3"), RoomData.RoomType.PRE_OPERATING_ROOM.toString()),
            ProcessData.RoomWithType(RoomData.RoomId("room4"), RoomData.RoomType.OPERATING_ROOM.toString()),
            ProcessData.ProcessState.PRE_SURGERY,
            ProcessData.ProcessStep.ANESTHESIA,
        ) shouldNotBe null
    }

    "A Surgical Process should not have an empty process Id" {
        shouldThrow<IllegalArgumentException> {
            ProcessData.ProcessId("")
        }
    }
})
