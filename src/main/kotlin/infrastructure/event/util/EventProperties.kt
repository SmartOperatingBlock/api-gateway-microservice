/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.event.util

/**
 * Module with some event properties.
 */
object EventProperties {

    /**
     * Module with all event keys.
     */
    object EventKeys {
        /** The key for automation proposal event. */
        const val automationProposalsEventKey = "MEDICAL_TECHNOLOGY_AUTOMATION_PROPOSAL_EVENT"
    }

    /**
     * Module with all topics.
     */
    object Topics {
        /** The topic of process manual events. */
        const val processManualEventTopic = "process-manual-events"

        /** The topic for automation requests events. */
        const val automationRequestsEventsTopic = "automation-requests-events"

        /** The topic for medical technologies automation requests events. */
        const val medicalTechnologyAutomationRequestsTopic = "medical-technology-automation-requests-events"

        /** The topic for stop custom lights events. */
        const val stopCustomLightTopic = "stop-custom-light-events"

        /** The topic for  automation proposals events. */
        const val automationProposalsEventsTopic = "automation-proposals-events"
    }
}
