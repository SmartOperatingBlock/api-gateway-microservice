/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity

/**
 * Models the surgery report info.
 * @param surgeryId the id of the surgery.
 * @param surgeryName the name of the surgery.
 * @param patientName the name of the patient.
 */
data class SurgeryReportInfo(
    val surgeryId: SurgeryId,
    val surgeryName: SurgeryName,
    val patientName: PatientName,
)

/**
 * The [id] of the surgery.
 */
data class SurgeryId(val id: String) {
    init {
        require(id.isNotEmpty())
    }
}

/**
 * The [surgeryName].
 */
data class SurgeryName(val surgeryName: String) {
    init {
        require(surgeryName.isNotEmpty())
    }
}

/**
 * The [patientName].
 */
data class PatientName(val patientName: String) {
    init {
        require(patientName.isNotEmpty())
    }
}
