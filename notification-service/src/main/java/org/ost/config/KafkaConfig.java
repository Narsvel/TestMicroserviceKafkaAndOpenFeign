package org.ost.config;

import com.example.avro.ClientAvro;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    final Environment environment;

    public KafkaConfig(Environment environment) {
        this.environment = environment;
    }

    Map<String, Object> producerConfig() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                environment.getRequiredProperty("spring.kafka.producer.bootstrap-servers"));
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                environment.getRequiredProperty("spring.kafka.producer.key-serializer"));
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                environment.getRequiredProperty("spring.kafka.producer.value-serializer"));
        config.put(ProducerConfig.ACKS_CONFIG,
                environment.getRequiredProperty("spring.kafka.producer.acks"));
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,
                environment.getRequiredProperty("spring.kafka.producer.properties.delivery.timeout.ms"));
        config.put(ProducerConfig.LINGER_MS_CONFIG,
                environment.getRequiredProperty("spring.kafka.producer.properties.linger.ms"));
        config.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,
                environment.getRequiredProperty("spring.kafka.producer.properties.request.timeout.ms"));
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
                environment.getRequiredProperty("spring.kafka.producer.properties.enable.idempotence"));
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,
                environment.getRequiredProperty("spring.kafka.producer.properties.max.in.flight.per.connection"));
        config.put("schema.registry.url",
                environment.getProperty("spring.kafka.producer.properties.schema.registry.url",
                        "http://localhost:8085"));  //Avro schema registry
        config.put("auto.register.schemas", "true");
        config.put("use.latest.version", "true");

        return config;
    }

    @Bean
    ProducerFactory<String, ClientAvro> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    KafkaTemplate<String, ClientAvro> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("client-created-topic")
                .partitions(3)
                .replicas(2)
                .configs(Map.of("min.insync.replicas", "1"))
                .build();
    }

}
