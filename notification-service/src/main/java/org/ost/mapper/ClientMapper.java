package org.ost.mapper;

import com.example.avro.ClientAvro;
import org.ost.dto.ClientDTO;
import org.ost.models.Client;
import org.ost.services.event.ClientCreatedEvent;
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

    public ClientAvro mapToClientAvro(Client client) {
        return ClientAvro.newBuilder()
                .setId(client.getId())
                .setName(client.getName())
                .setEmail(client.getEmail())
                .setAge(client.getAge())
                .build();
    }
}
