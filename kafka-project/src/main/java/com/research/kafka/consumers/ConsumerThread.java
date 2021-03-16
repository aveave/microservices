package com.research.kafka.consumers;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerThread implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerThread.class);

    private final CountDownLatch latch;
    private final KafkaConsumer<String, String> consumer;
    private Properties properties;

    public ConsumerThread(CountDownLatch latch, String bootstrapServer, String groupId, String topic) {
        configureProperties(bootstrapServer, groupId);
        this.latch = latch;
        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton(topic));

    }

    private void configureProperties(String bootstrapServer, String groupId) {
        properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    }

    @Override
    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record: records) {
                    LOGGER.info("Value: " + record.value() + "\n" + "Partition: " + record.partition());
                }
            }
        } catch (WakeupException e) {
            LOGGER.info("Received shutdown signal!");
        } finally {
            consumer.close();
            latch.countDown();
        }
    }

    public void shutdown() {
        consumer.wakeup();
    }
}
