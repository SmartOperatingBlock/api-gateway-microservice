/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.event

import application.presenter.event.ProcessEvent
import application.presenter.event.payload.ProcessEventPayloads.CustomLightRequestEvent
import application.presenter.event.payload.ProcessEventPayloads.MedicalTechnologyAutomationRequestEvent
import application.presenter.event.payload.ProcessEventPayloads.ProcessManualEvent
import application.presenter.event.payload.ProcessEventPayloads.StopCustomLightEvent
import infrastructure.event.handlers.KafkaEventHandler
import infrastructure.event.util.EventProperties.Topics
import infrastructure.event.util.KafkaProperties.consumerProperties
import infrastructure.event.util.KafkaProperties.producerProperties
import io.vertx.core.AbstractVerticle
import io.vertx.kafka.client.consumer.KafkaConsumer
import io.vertx.kafka.client.producer.KafkaProducer
import io.vertx.kafka.client.producer.KafkaProducerRecord
import kotlinx.serialization.json.Json

/**
 * The Verticle responsible to consume and publish events on an Event Broker.
 */
class EventManagerVerticle : AbstractVerticle() {

    init {
        listOf(BOOTSTRAP_SERVER_URL, SCHEMA_REGISTRY_URL).forEach {
            requireNotNull(it) {
                "Invalid environment variable: $it"
            }
        }
    }

    override fun start() {
        val kafkaProducer: KafkaProducer<String, String> =
            KafkaProducer.create(vertx, producerProperties(BOOTSTRAP_SERVER_URL, SCHEMA_REGISTRY_URL))
        val kafkaConsumer: KafkaConsumer<String, String> =
            KafkaConsumer.create(vertx, consumerProperties(BOOTSTRAP_SERVER_URL, SCHEMA_REGISTRY_URL))

        this.vertx.eventBus().consumer(Topics.processManualEventTopic) { message ->
            val event = Json.decodeFromString<ProcessEvent<ProcessManualEvent>>(message.body())
            kafkaProducer.send(KafkaProducerRecord.create(Topics.processManualEventTopic, event.key, message.body()))
        }

        this.vertx.eventBus().consumer(Topics.automationRequestsEventsTopic) { message ->
            val event = Json.decodeFromString<ProcessEvent<CustomLightRequestEvent>>(message.body())
            kafkaProducer.send(
                KafkaProducerRecord.create(Topics.automationRequestsEventsTopic, event.key, message.body()),
            )
        }

        this.vertx.eventBus().consumer(Topics.stopCustomLightTopic) { message ->
            val event = Json.decodeFromString<ProcessEvent<StopCustomLightEvent>>(message.body())
            kafkaProducer.send(
                KafkaProducerRecord.create(Topics.automationRequestsEventsTopic, event.key, message.body()),
            )
        }

        this.vertx.eventBus().consumer(Topics.medicalTechnologyAutomationRequestsTopic) { message ->
            val event = Json.decodeFromString<ProcessEvent<MedicalTechnologyAutomationRequestEvent>>(message.body())
            kafkaProducer.send(
                KafkaProducerRecord.create(Topics.automationRequestsEventsTopic, event.key, message.body()),
            )
        }

        kafkaConsumer.subscribe(Topics.automationProposalsEventsTopic)
        kafkaConsumer.handler(KafkaEventHandler(vertx))
    }

    companion object {
        private val BOOTSTRAP_SERVER_URL = System.getenv("BOOTSTRAP_SERVER_URL")
        private val SCHEMA_REGISTRY_URL = System.getenv("SCHEMA_REGISTRY_URL")
    }
}
