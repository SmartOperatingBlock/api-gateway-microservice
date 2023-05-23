/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event.payload

import application.presenter.event.ProcessEvent

/** Module that wraps all payloads of process events. */
object ProcessEventPayloads {

    /** Interface that identify a data payload that is accepted inside a [ProcessEvent]. */
    interface ProcessEventPayload

    /**
     * The payload of a process manual event with the [roomId] and the [manualEvent].
     */
    data class ProcessManualEvent(val roomId: String, val manualEvent: String) : ProcessEventPayload
}
