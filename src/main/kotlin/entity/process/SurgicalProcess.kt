/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.process

import java.time.Instant

/**
 * The model of a Surgical Process composed by:
 * - the [id]
 * - the [dateTime]
 * - the [type]
 * - the [patientId]
 * - the [healthProfessionalId]
 * - the [preOperatingRoom]
 * - the [operatingRoom]
 * - the [state]
 * - the [step].
 */
data class SurgicalProcess(
    val id: ProcessData.ProcessId,
    val dateTime: Instant,
    val type: String,
    val patientId: ProcessData.PatientId,
    val healthProfessionalId: ProcessData.HealthProfessionalId,
    val preOperatingRoom: ProcessData.RoomWithType,
    val operatingRoom: ProcessData.RoomWithType,
    val state: ProcessData.ProcessState,
    val step: ProcessData.ProcessStep,
)
