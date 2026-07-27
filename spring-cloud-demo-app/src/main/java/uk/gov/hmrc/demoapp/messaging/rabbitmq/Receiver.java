package uk.gov.hmrc.demoapp.messaging.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import uk.gov.hmrc.demoapp.service.RabbitMqService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Receiver {

    private final RabbitMqService rabbitMqService;

    public Receiver(RabbitMqService rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }

    @RabbitListener(id = "hawk-receiver", queues = {"${hawk.rabbitmq.queueName}"})
    public void receiveMessage(@Payload String message) {
        log.info("Received message: " + message);
        rabbitMqService.storeReceivedMessage(message);
    }
}