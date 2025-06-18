package org.ost.services;

import org.ost.dto.ClientDTO;

import java.util.concurrent.ExecutionException;

public interface ClientsService {
    ClientDTO create(ClientDTO clientDTO) throws ExecutionException, InterruptedException;
}
