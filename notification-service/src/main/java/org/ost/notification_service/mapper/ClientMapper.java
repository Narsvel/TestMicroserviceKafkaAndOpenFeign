package org.ost.notification_service.mapper;

import org.ost.notification_service.dto.ClientDTO;
import org.ost.notification_service.models.Client;
import org.ost.notification_service.services.event.ClientCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client mapToClient(ClientDTO clientDTO) {
        return Client.builder()
                .id(clientDTO.getId())
                .name(clientDTO.getName())
                .email(clientDTO.getEmail())
                .age(clientDTO.getAge())
                .build();
    }

    public ClientDTO mapToClientDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .age(client.getAge())
                .build();
    }

    public ClientCreatedEvent mapToClientCreatedEvent(Client client) {
        return ClientCreatedEvent.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .age(client.getAge())
                .build();
    }
}
