/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.payload

import application.presenter.event.ProcessEvent
import kotlinx.serialization.Serializable

/** Module that wraps all payloads of process events. */
object ProcessEventPayloads {

    /** Interface that identify a data payload that is accepted inside a [ProcessEvent]. */
    interface ProcessEventPayload

    /**
     * The payload of a process manual event with the [roomId] and the [manualEvent].
     */
    @Serializable
    data class ProcessManualEvent(val roomId: String, val manualEvent: String) : ProcessEventPayload

    /**
     * The payload of custom light request event composed by the [roomId], [ambientLightLux] and [surgicalLightLux].
     */
    @Serializable
    data class CustomLightRequestEvent(
        val roomId: String,
        val ambientLightLux: Int,
        val surgicalLightLux: Int,
    ) : ProcessEventPayload

    /**
     * The payload of stop custom light request event composed by the [roomId].
     */
    @Serializable
    data class StopCustomLightEvent(val roomId: String) : ProcessEventPayload

    /**
     * The payload for medical technology automation request events composed by [roomId] and [medicalTechnologyType].
     */
    @Serializable
    data class MedicalTechnologyAutomationRequestEvent(
        val roomId: String,
        val medicalTechnologyType: String,
    ) : ProcessEventPayload

    /**
     * The payload of automation proposal event composed by the [roomId], [medicalTechnologyType],
     * [ambientLightLux] and [surgicalLightLux].
     */
    @Serializable
    data class MedicalTechnologyAutomationProposalEvent(
        val roomId: String,
        val medicalTechnologyType: String,
        val ambientLightLux: Int,
        val surgicalLightLux: Int,
    ) : ProcessEventPayload
}
