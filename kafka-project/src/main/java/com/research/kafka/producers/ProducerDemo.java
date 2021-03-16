package com.research.kafka.producers;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemo {

    public static void main(String[] args) {
        String bootstrapServers = "127.0.01:9092";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create a producer record

        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "hello_world");

        // send data - async
        producer.send(record);

        producer.flush();
        producer.close();
    }
}
