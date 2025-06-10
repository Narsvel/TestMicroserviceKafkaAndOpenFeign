package org.ost.notification_service.repositories;

import org.ost.notification_service.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Client, Integer> {
}
