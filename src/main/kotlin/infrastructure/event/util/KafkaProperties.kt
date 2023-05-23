/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.event.util

/**
 * Module with all properties for send and receive events on a Kafka event broker.
 */
object KafkaProperties {

    /**
     * The properties of a KafkaProducer.
     */
    fun producerProperties(bootstrapServerUrl: String, schemaRegistryUrl: String): Map<String, String> = mutableMapOf(
        "bootstrap.servers" to bootstrapServerUrl,
        "schema.registry.url" to schemaRegistryUrl,
        "key.serializer" to "org.apache.kafka.common.serialization.StringSerializer",
        "value.serializer" to "org.apache.kafka.common.serialization.StringSerializer",
        "acks" to "1",
    )

    /**
     * The properties of a KafkaConsumer.
     */
    fun consumerProperties(bootstrapServerUrl: String, schemaRegistryUrl: String): Map<String, String> = mutableMapOf(
        "bootstrap.servers" to bootstrapServerUrl,
        "schema.registry.url" to schemaRegistryUrl,
        "key.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
        "value.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
        "group.id" to "verticle-consumer",
        "enable.auto.commit" to "false",
    )
}
