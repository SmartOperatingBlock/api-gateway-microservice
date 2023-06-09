/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api.report.medicaldevice

import kotlinx.serialization.Serializable

/**
 * Presenter class for the [entity.surgeryreport.medicaldevice.ImplantableMedicalDevice] class.
 * It returns: the [id] of the implantable medical device and the [type]
 */
@Serializable
data class ImplantableMedicalDeviceApiDto(
    val id: String,
    val type: ImplantableMedicalDeviceApiDtoType,
)

/**
 * Presenter enum class to represent [entity.surgeryreport.medicaldevice.ImplantableMedicalDeviceType].
 */
enum class ImplantableMedicalDeviceApiDtoType {
    /** The Catheter is an implantable medical device type. */
    CATHETER,

    /** The pacemaker is an implantable medical device type. */
    PACEMAKER,
}
