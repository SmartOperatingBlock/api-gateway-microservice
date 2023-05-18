/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.process

import entity.room.RoomData
import kotlinx.serialization.Serializable

/**
 * Module with all data for surgical processes.
 */
object ProcessData {

    /**
     * The [id] of the Surgical Process.
     */
    data class ProcessId(val id: String) {
        init {
            require(id.isNotEmpty()) {
                "Process id cannot be empty!"
            }
        }
    }

    /**
     * The [id] of the Patient.
     */
    data class PatientId(val id: String) {
        init {
            require(id.isNotEmpty()) {
                "Patient id cannot be empty!"
            }
        }
    }

    /**
     * The [id] of the Health Professional.
     */
    data class HealthProfessionalId(val id: String) {
        init {
            require(id.isNotEmpty()) {
                "Health Professional id cannot be empty!"
            }
        }
    }

    /**
     * The type of room inside the Operating Block composed by [id] and [type].
     */
    @Serializable
    data class RoomWithType(
        val id: RoomData.RoomId,
        val type: String,
    )

    /** The different states of a [SurgicalProcess]. */
    enum class ProcessState {
        PRE_SURGERY, SURGERY, POST_SURGERY, INTERRUPTED, TERMINATED
    }

    /** The different steps of a [SurgicalProcess]. */
    enum class ProcessStep {
        PATIENT_IN_PREPARATION,
        PATIENT_ON_OPERATING_TABLE,
        ANESTHESIA,
        SURGERY_IN_PROGRESS,
        END_OF_SURGERY,
        PATIENT_UNDER_OBSERVATION,
    }
}
