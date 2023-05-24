/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.serialization

import application.presenter.api.report.medicaldevice.ImplantableMedicalDeviceApiDto
import application.presenter.api.report.medicaldevice.ImplantableMedicalDeviceApiDtoType
import application.presenter.api.report.medicaldevice.MedicalTechnologyApiDto
import application.presenter.api.report.medicaldevice.MedicalTechnologyApiDtoType
import entity.surgeryreport.medicaldevice.ImplantableMedicalDevice
import entity.surgeryreport.medicaldevice.ImplantableMedicalDeviceType
import entity.surgeryreport.medicaldevice.MedicalTechnology
import entity.surgeryreport.medicaldevice.MedicalTechnologyType

/**
 * Serializers for data to return to API.
 */
object MedicalDeviceSerializer {
    /**
     * Extension method to obtain the api dto of an implantable medical device.
     */
    fun ImplantableMedicalDevice.toApiDto(): ImplantableMedicalDeviceApiDto = ImplantableMedicalDeviceApiDto(
        id = this.id.value,
        type = this.type.toApiDtoType(),
    )

    private fun ImplantableMedicalDeviceType.toApiDtoType(): ImplantableMedicalDeviceApiDtoType = when (this) {
        ImplantableMedicalDeviceType.CATHETER -> ImplantableMedicalDeviceApiDtoType.CATHETER
        ImplantableMedicalDeviceType.PACEMAKER -> ImplantableMedicalDeviceApiDtoType.PACEMAKER
    }

    /**
     * Extension method to obtain the api dto of a medical technology.
     */
    fun MedicalTechnology.toApiDto(): MedicalTechnologyApiDto = MedicalTechnologyApiDto(
        id = this.id.value,
        name = this.name,
        description = this.description,
        type = this.type.toApiDtoType(),
        inUse = this.inUse,
    )

    private fun MedicalTechnologyType.toApiDtoType(): MedicalTechnologyApiDtoType = when (this) {
        MedicalTechnologyType.ENDOSCOPE -> MedicalTechnologyApiDtoType.ENDOSCOPE
        MedicalTechnologyType.XRAY -> MedicalTechnologyApiDtoType.XRAY
    }
}
