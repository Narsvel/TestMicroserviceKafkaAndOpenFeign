package org.ost.services.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreatedEvent implements Serializable {
    private int id;
    private String name;
    private String email;
    private int age;
}
