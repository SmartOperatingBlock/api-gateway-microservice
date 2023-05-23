/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.event

import application.presenter.event.payload.ProcessEventPayloads
import kotlinx.serialization.Serializable

/** The model of a Process Event composed by:
 *  - the [key]
 *  - the [data]
 *  - the [dateTime].
 */
@Serializable
data class ProcessEvent<E : ProcessEventPayloads.ProcessEventPayload>(
    override val key: String,
    override val data: E,
    override val dateTime: String,
) : Event<E>
