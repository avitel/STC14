package ru.inno.lec20.HW;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class MainProducer {
    public static void main(String[] args) {

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("delivery.timeout.ms", 30001);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer producerWrapper = new Producer(props, "test");

        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        int key = 0;

        while (true){

            String message = null;
            try {
                message = keyboardReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            if ("quit".equals(message)) break;
            producerWrapper.sendMessage(Integer.toString(key), message);
            key++;
        }
        producerWrapper.close();
    }
}
