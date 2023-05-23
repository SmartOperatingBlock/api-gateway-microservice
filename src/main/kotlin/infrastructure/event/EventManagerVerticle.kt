/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.event

import application.presenter.event.Event
import application.presenter.event.ProcessEvent
import application.presenter.event.payload.ProcessEventPayloads
import infrastructure.event.util.KafkaProperties.consumerProperties
import infrastructure.event.util.KafkaProperties.producerProperties
import infrastructure.provider.Provider
import io.vertx.core.AbstractVerticle
import io.vertx.kafka.client.consumer.KafkaConsumer
import io.vertx.kafka.client.producer.KafkaProducer
import io.vertx.kafka.client.producer.KafkaProducerRecord
import kotlinx.serialization.json.Json

/**
 * The Verticle responsible to consume and publish events on an Event Broker.
 */
class EventManagerVerticle(
    private val provider: Provider,
) : AbstractVerticle() {

    init {
        listOf(BOOTSTRAP_SERVER_URL, SCHEMA_REGISTRY_URL).forEach {
            requireNotNull(it) {
                "Invalid environment variable: $it"
            }
        }
    }

    override fun start() {
        val kafkaProducer: KafkaProducer<String, Event<*>> =
            KafkaProducer.create(vertx, producerProperties(BOOTSTRAP_SERVER_URL, SCHEMA_REGISTRY_URL))
        val kafkaConsumer: KafkaConsumer<String, String> =
            KafkaConsumer.create(vertx, consumerProperties(BOOTSTRAP_SERVER_URL, SCHEMA_REGISTRY_URL))

        this.vertx.eventBus().consumer(processManualEventTopic) { message ->
            val event = Json.decodeFromString<ProcessEvent<ProcessEventPayloads.ProcessManualEvent>>(message.body())
            val producerRecord: KafkaProducerRecord<String, Event<*>> =
                KafkaProducerRecord.create(processManualEventTopic, event.key, event)
            kafkaProducer.send(producerRecord)
        }

        provider.webClient
        kafkaConsumer.asStream()
    }

    companion object {
        private val BOOTSTRAP_SERVER_URL = System.getenv("BOOTSTRAP_SERVER_URL")
        private val SCHEMA_REGISTRY_URL = System.getenv("SCHEMA_REGISTRY_URL")
        private const val processManualEventTopic = "process-manual-events"
    }
}
