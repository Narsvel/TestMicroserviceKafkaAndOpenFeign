package org.ost.notification_service.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ost.notification_service.dto.ClientDTO;
import org.ost.notification_service.mapper.ClientMapper;
import org.ost.notification_service.models.Client;
import org.ost.notification_service.repositories.ClientsRepository;
import org.ost.notification_service.services.event.ClientCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ClientsServiceImpl implements ClientsService{

    private final ClientsRepository clientsRepository;
    private final ClientMapper clientMapper;
    private final KafkaTemplate<String, ClientCreatedEvent> kafkaTemplate;

    @Override
    public ClientDTO create(ClientDTO clientDTO) throws ExecutionException, InterruptedException {
        Client saveClient = clientsRepository.save(clientMapper.mapToClient(clientDTO));

        SendResult<String, ClientCreatedEvent> result = kafkaTemplate
                .send("client-created-topic", clientMapper.mapToClientCreatedEvent(saveClient)).get();

        log.info("RecordMetadata: {}", result.getRecordMetadata());

        return clientMapper.mapToClientDTO(saveClient);
    }
}
