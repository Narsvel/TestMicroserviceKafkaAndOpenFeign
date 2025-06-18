package org.ost.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientErrorResponse {
    private String message;
    private long timestamp;
}
