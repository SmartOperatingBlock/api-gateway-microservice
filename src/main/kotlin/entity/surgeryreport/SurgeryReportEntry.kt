/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity.surgeryreport

import kotlinx.serialization.Contextual
import java.time.Instant

/**
 * Class that model the principal information of a surgery report in the surgery report archive.
 * @param surgicalProcessID the id of the surgical process.
 * @param patientId the id of the patient.
 * @param patientName the name of the patient.
 * @param patientSurname the surname of the patient.
 * @param surgicalProcessDescription the description of the surgical process.
 * @param surgeryDate the date of the surgical process.
 */
data class SurgeryReportEntry(
    val surgicalProcessID: ProcessId,
    val patientId: PatientId,
    val patientName: PatientName?,
    val patientSurname: PatientSurname?,
    val surgicalProcessDescription: SurgicalProcessDescription,
    @Contextual val surgeryDate: SurgeryDate,
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
 * The [id] of the patient.
 */
data class PatientId(val id: String) {
    init {
        require(id.isNotEmpty())
    }
}

/**
 * The [surname] of the patient.
 */
data class PatientSurname(val surname: String) {
    init {
        require(surname.isNotEmpty())
    }
}

/**
 * The [description] of the surgical process.
 */
data class SurgicalProcessDescription(val description: String) {
    init {
        require(description.isNotEmpty())
    }
}

/**
 * The [date] of the surgical process.
 */
data class SurgeryDate(val date: Instant)
