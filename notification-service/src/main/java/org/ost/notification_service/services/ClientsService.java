package org.ost.notification_service.services;

import org.ost.notification_service.dto.ClientDTO;

import java.util.concurrent.ExecutionException;

public interface ClientsService {
    ClientDTO create(ClientDTO clientDTO) throws ExecutionException, InterruptedException;
}
