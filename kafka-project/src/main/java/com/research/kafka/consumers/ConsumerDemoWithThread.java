package com.research.kafka.consumers;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemoWithThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerDemoWithThread.class);

    private static final String bootstrapServer = "localhost:9092";

    private static final String groupId = "new_group_id";

    private static final String topic = "new_topic";

    private static final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        ConsumerThread consumerThread = new ConsumerThread(latch, bootstrapServer, groupId, topic);
        Thread thread = new Thread(consumerThread);
        thread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("Caught shutdown hook");
            consumerThread.shutdown();
        }));

        try {
            latch.await();
        } catch (InterruptedException e) {
            LOGGER.error("App was interrupted", e);
        } finally {
            LOGGER.info("Application is closing");
        }
    }

}
