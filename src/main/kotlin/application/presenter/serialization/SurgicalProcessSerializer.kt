/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.serialization

import application.presenter.api.report.process.SurgicalProcessStepApiDto
import entity.surgeryreport.process.SurgicalProcessStep

/**
 * Serializers for data to return to API.
 */
object SurgicalProcessSerializer {
    /**
     * Extension method to obtain the api dto of the surgical process step.
     */
    fun SurgicalProcessStep.toApiDto(): SurgicalProcessStepApiDto = when (this) {
        SurgicalProcessStep.PATIENT_IN_PREPARATION -> SurgicalProcessStepApiDto.PATIENT_IN_PREPARATION
        SurgicalProcessStep.PATIENT_ON_OPERATING_TABLE -> SurgicalProcessStepApiDto.PATIENT_ON_OPERATING_TABLE
        SurgicalProcessStep.ANESTHESIA -> SurgicalProcessStepApiDto.ANESTHESIA
        SurgicalProcessStep.SURGERY_IN_PROGRESS -> SurgicalProcessStepApiDto.SURGERY_IN_PROGRESS
        SurgicalProcessStep.END_OF_SURGERY -> SurgicalProcessStepApiDto.END_OF_SURGERY
        SurgicalProcessStep.PATIENT_UNDER_OBSERVATION -> SurgicalProcessStepApiDto.PATIENT_UNDER_OBSERVATION
    }

    /**
     * Extension method to obtain the api dto of the surgical process step.
     */
    fun SurgicalProcessStepApiDto.toSurgicalProcessStep(): SurgicalProcessStep = when (this) {
        SurgicalProcessStepApiDto.PATIENT_IN_PREPARATION -> SurgicalProcessStep.PATIENT_IN_PREPARATION
        SurgicalProcessStepApiDto.PATIENT_ON_OPERATING_TABLE -> SurgicalProcessStep.PATIENT_ON_OPERATING_TABLE
        SurgicalProcessStepApiDto.ANESTHESIA -> SurgicalProcessStep.ANESTHESIA
        SurgicalProcessStepApiDto.SURGERY_IN_PROGRESS -> SurgicalProcessStep.SURGERY_IN_PROGRESS
        SurgicalProcessStepApiDto.END_OF_SURGERY -> SurgicalProcessStep.END_OF_SURGERY
        SurgicalProcessStepApiDto.PATIENT_UNDER_OBSERVATION -> SurgicalProcessStep.PATIENT_UNDER_OBSERVATION
    }
}
