package ru.inno.lec20.HW;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Producer for Kafka. Can send message and close.
 *
 * firstly you have to
 * 1. download and unpack https://www.apache.org/dyn/closer.cgi?path=/kafka/2.1.0/kafka_2.11-2.1.0.tgz
 * 2. start ZooKeeper bin/zookeeper-server-start.sh config/zookeeper.properties
 * 3. start Kafka server bin/kafka-server-start.sh config/server.properties
 *
 */
public class Producer {

    private org.apache.kafka.clients.producer.Producer producer;
    private String topic;

    public Producer(Properties props, String topic) {
        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void sendMessage(String key, String message){

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);

        producer.send(record);

    }

    public void close(){
        producer.close();
    }
}
