package com.research.kafka.producers;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemoKeys {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerDemoKeys.class);

    public static void main(String[] args) {
        Callback kafkaCallBack = (RecordMetadata recordMetadata, Exception e) -> {
            if (e == null) {
                LOGGER.info("Received new metadata \n" +
                        "Topic: " + recordMetadata.topic() + "\n" +
                        "Partition: " + recordMetadata.partition() + "\n" +
                        "Offset: " + recordMetadata.offset() + "\n" +
                        "Timestamp: " + recordMetadata.timestamp());
            } else {
                LOGGER.error("Error while producing", e);
            }
        };

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        for (int i =0; i < 10; i++) {

            String key = "id_" + i;

            LOGGER.info("Key: " + key);

            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("new_topic", key, "hello" + i);
            producer.send(producerRecord, kafkaCallBack);
        }
        producer.close();

    }
}
