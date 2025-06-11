package org.ost.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientKafkaConsumer {

    @KafkaListener(topics = "client-created-topic")
    public void consume(ConsumerRecord<String, String> record) {
        log.info(record.value());
    }
}
