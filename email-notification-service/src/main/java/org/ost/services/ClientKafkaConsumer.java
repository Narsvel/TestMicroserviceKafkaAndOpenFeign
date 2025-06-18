package org.ost.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ost.services.event.ClientCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientKafkaConsumer {

    @KafkaListener(topics = "client-created-topic", groupId = "email-notification-group")
    public void consume(ConsumerRecord<String, ClientCreatedEvent> record) {
        log.info(record.value().toString());
    }
}
