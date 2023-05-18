/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.report

import java.time.Instant

/**
 * Class that model the principal information of a surgery report in the surgery report archive.
 * @param processId the id of the surgery process.
 * @param patientInfo the name and the surname of the patient.
 * @param processType the type of the surgery.
 * @param processDate the date of the surgery.
 */
data class SurgeryReportEntry(
    val processId: ProcessId,
    val patientInfo: PatientInfo,
    val processType: ProcessType,
    val processDate: ProcessDate,
)

/**
 * The [id] of the process.
 */
data class ProcessId(val id: String) {
    init {
        require(id.isNotEmpty())
    }
}

/**
 * The [patientInfo].
 */
data class PatientInfo(val patientInfo: String) {
    init {
        require(patientInfo.isNotEmpty())
    }
}

/**
 * The [processType].
 */
data class ProcessType(val processType: String) {
    init {
        require(processType.isNotEmpty())
    }
}

/**
 * The [processDate].
 */
data class ProcessDate(val processDate: Instant) {
    init {
        require(processDate.isBefore(Instant.now()))
    }
}
