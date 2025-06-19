package org.ost.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.ost.dto.ClientDTO;
import org.ost.exception.ClientNotCreatedException;
import org.ost.dto.response.ClientErrorResponse;
import org.ost.services.ClientsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsServiceImpl clientsService;

    public ClientsController(ClientsServiceImpl clientsService) {
        this.clientsService = clientsService;
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientDTO clientDTO,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new ClientNotCreatedException(errorMsg.toString());
        }

        ClientDTO saveClient;
        try {
            saveClient = clientsService.create(clientDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(clientDTO);
        }
        return new ResponseEntity<>(saveClient, HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(ClientNotCreatedException e) {
        ClientErrorResponse response = new ClientErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        // В HTTP ответе будет тело response и статус в заголовке 400
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //BAD_REQUEST - 400 статус
    }


}
