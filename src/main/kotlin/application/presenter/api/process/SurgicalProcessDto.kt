/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.process

import entity.process.ProcessData
import entity.process.SurgicalProcess
import kotlinx.serialization.Serializable
import java.time.Instant

/**
 * The model of a Surgical Process Dto composed by:
 * - the [processId]
 * - the [dateTime]
 * - the [type]
 * - the [patientId]
 * - the [healthProfessionalId]
 * - the [preOperatingRoom]
 * - the [operatingRoom]
 * - the [state]
 * - the [step].
 */
@Serializable
data class SurgicalProcessDto(
    val processId: String,
    val dateTime: String,
    val type: String,
    val patientId: String,
    val healthProfessionalId: String,
    val preOperatingRoom: ProcessData.RoomWithType,
    val operatingRoom: ProcessData.RoomWithType,
    val state: String,
    val step: String,
)

/**
 * Extension method to convert a [SurgicalProcess] to its [SurgicalProcessDto].
 */
fun SurgicalProcess.toSurgicalProcessDto() = SurgicalProcessDto(
    this.id.id,
    this.dateTime.toString(),
    this.type,
    this.patientId.id,
    this.healthProfessionalId.id,
    this.preOperatingRoom,
    this.operatingRoom,
    this.state.toString(),
    this.step.toString(),
)

/**
 * Extension method to convert a [SurgicalProcessDto] to its [SurgicalProcess].
 */
fun SurgicalProcessDto.toSurgicalProcess() = SurgicalProcess(
    ProcessData.ProcessId(this.processId),
    Instant.parse(this.dateTime),
    this.type,
    ProcessData.PatientId(this.patientId),
    ProcessData.HealthProfessionalId(this.healthProfessionalId),
    this.preOperatingRoom,
    this.operatingRoom,
    ProcessData.ProcessState.valueOf(this.state),
    ProcessData.ProcessStep.valueOf(this.step),
)
