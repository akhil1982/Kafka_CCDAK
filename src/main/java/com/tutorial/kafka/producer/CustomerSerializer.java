package com.tutorial.kafka.producer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class CustomerSerializer implements Serializer<Customer> {

    @Override
    public  void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public void close(){}
    @Override
    public byte[] serialize (String s, Customer customer) {
        try {
            byte[] serializedName = new byte[0];
            int stringsize = 0;

            if (customer == null) {
                return null;
            } else {
                if (customer.getName() != null) {
                    serializedName = customer.getName().getBytes();
                    stringsize = customer.getName().length();
                }
            }

            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringsize);
            buffer.putInt(customer.getId());
            buffer.putInt(stringsize);
            buffer.put(serializedName);
            return buffer.array();
        } catch (SerializationException se){
            se.printStackTrace();
            throw new SerializationException("Serialization exception " +
                    "received "+ se.getMessage());
        }
    }
}
