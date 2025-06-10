package org.ost.notification_service.services.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreatedEvent {
    private int id;
    private String name;
    private String email;
    private int age;
}
