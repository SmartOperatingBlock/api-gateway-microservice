/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.event.handlers

import application.presenter.event.ProcessEvent
import application.presenter.event.payload.ProcessEventPayloads.MedicalTechnologyAutomationProposalEvent
import infrastructure.event.util.EventProperties
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.kafka.client.consumer.KafkaConsumerRecord
import kotlinx.serialization.json.Json

/** The Kafka Event Handler. */
class KafkaEventHandler(private val vertx: Vertx) : Handler<KafkaConsumerRecord<String, String>> {

    override fun handle(record: KafkaConsumerRecord<String, String>) {
        val event = Json.decodeFromString<ProcessEvent<MedicalTechnologyAutomationProposalEvent>>(record.value())
        when (event.key) {
            EventProperties.EventKeys.automationProposalsEventKey -> {
                vertx.eventBus().send(EventProperties.Topics.automationProposalsEventsTopic, record.value())
            }
        }
    }
}
