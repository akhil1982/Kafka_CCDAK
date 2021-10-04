package com.tutorial.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SimpleCallaback implements Callback {

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("Async message produced to the broker. "+recordMetadata.topic().toString());
    }

}
