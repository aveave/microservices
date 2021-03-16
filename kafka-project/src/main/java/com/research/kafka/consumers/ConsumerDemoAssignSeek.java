package com.research.kafka.consumers;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemoAssignSeek {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDemoAssignSeek.class);

    public static void main(String[] args) {
            // create consumer configs
            Properties properties = new Properties();
            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

            // assign
            TopicPartition partition = new TopicPartition("new_topic", 1);
            consumer.assign(Collections.singletonList(partition));

            // seek
            long offsetToReadFrom = 15;
            consumer.seek(partition, offsetToReadFrom);

            int numberOfMessagesToRead = 5;
            boolean keepOnReading = true;
            int numberOfMessagesReadSoFar = 0;

            while (keepOnReading) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord record: records) {
                    numberOfMessagesReadSoFar++;
                    LOGGER.info("Key: " + record.key() + "\n" +
                                "Topic: " + record.topic() + "\n" +
                                "Value: " + record.value());
                    if ( numberOfMessagesReadSoFar == numberOfMessagesToRead) {
                        keepOnReading = false;
                        break;
                    }
                }
            }
            LOGGER.info("Exiting the application");
    }
}
