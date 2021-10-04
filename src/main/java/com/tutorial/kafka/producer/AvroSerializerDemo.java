package com.tutorial.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class AvroSerializerDemo {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer","io.confluent.kafka.serializers" +
                ".KafkaAvroSerializer");
        props.put("value.serializer","io.confluent.kafka.serializers" +
                ".KafkaAvroSerializer");
        props.put("schema.registry.url","customer.json");

        Producer<String, Customer> producer = new KafkaProducer<String,
                Customer>(props);
        Customer customer = new Customer("Akhil Syal", 1);
        ProducerRecord<String, Customer> record = new ProducerRecord<String,
                Customer>("test28Sep2021",customer.getId()+"", customer);
        producer.send(record);
    }
}
