package ru.inno.lec20.HW;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Consumer for Kafka
 * Can get messages
 */
public class Consumer {

    private KafkaConsumer<String, String> consumer;
    private String topic;


    public Consumer(Properties props, String topic) {
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public ConsumerRecords<String, String> rereceiveMessage(){

        return consumer.poll(100);
    }
}
