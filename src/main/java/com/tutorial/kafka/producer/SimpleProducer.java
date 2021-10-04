package com.tutorial.kafka.producer;


import org.apache.kafka.clients.producer.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SimpleProducer {

    public static void main(String[] args) {

        String topic = "test28Sep2021";

        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers", "localhost:9092");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(prop);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "test1", "helloTest");
        //Synchronous send
        Future<RecordMetadata> future = producer.send(record);
        try {
            RecordMetadata data = future.get();
            System.out.println("Sync call "+data.offset());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Async Send
        producer.send(new ProducerRecord<String, String>(topic, "asyncKey",
                "Async message is sent"), new SimpleCallaback());
        producer.close();
    }


}
