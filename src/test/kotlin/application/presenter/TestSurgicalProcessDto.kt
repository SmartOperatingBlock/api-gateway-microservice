/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter

import application.presenter.api.process.toSurgicalProcess
import application.presenter.api.process.toSurgicalProcessDto
import entity.process.ProcessData
import entity.process.SurgicalProcess
import entity.room.RoomData
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.Instant

class TestSurgicalProcessDto : StringSpec({

    val surgicalProcess = SurgicalProcess(
        ProcessData.ProcessId("processId"),
        Instant.now(),
        "Colonscopy",
        ProcessData.PatientId("patient0"),
        ProcessData.HealthProfessionalId("hp-0"),
        ProcessData.RoomWithType(RoomData.RoomId("room3"), RoomData.RoomType.PRE_OPERATING_ROOM.toString()),
        ProcessData.RoomWithType(RoomData.RoomId("room4"), RoomData.RoomType.OPERATING_ROOM.toString()),
        ProcessData.ProcessState.PRE_SURGERY,
        ProcessData.ProcessStep.ANESTHESIA,
    )

    "A surgical process should be correctly converted into its dto" {
        surgicalProcess.toSurgicalProcessDto() shouldNotBe null
        surgicalProcess.toSurgicalProcessDto().processId shouldBeEqual surgicalProcess.id.id
    }

    "A surgical process dto should be correctly converted into its entity" {
        surgicalProcess.toSurgicalProcessDto().toSurgicalProcess() shouldNotBe null
        surgicalProcess.toSurgicalProcessDto().toSurgicalProcess() shouldBe surgicalProcess
    }
})
