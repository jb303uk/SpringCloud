package uk.gov.hmrc.hawk.demo.demoapp.messaging.rabbitmq;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import uk.gov.hmrc.demoapp.service.RabbitMqService;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@Testcontainers
class RabbitTest {

    @Container
    static RabbitMQContainer container = new RabbitMQContainer(DockerImageName.parse("rabbitmq")
            .withRegistry("central-docker-remote.artifactory.alm.corp.hmrc.gov.uk")
            .withTag("3-management-alpine")
            .asCompatibleSubstituteFor("rabbitmq"));

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", container::getHost);
        registry.add("spring.rabbitmq.port", container::getAmqpPort);
        registry.add("spring.rabbitmq.username", container::getAdminUsername);
        registry.add("spring.rabbitmq.password", container::getAdminPassword);
        registry.add("spring.rabbitmq.virtual-host", ""::toString);
        registry.add("spring.rabbitmq.dynamic", "true"::toString);
    }

    @Autowired
    private RabbitMqService service;

    
    @Disabled("Testing Build before Unit Tests in Kubernetes Runner")
    void rabbitTest() {

        service.sendMessage("Test Message");
        String message = await().until(() -> service.getLastReceivedMessage(), Objects::nonNull);
        assertThat(message).isEqualTo("Test Message");
    }
}
