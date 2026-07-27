package uk.gov.hmrc.demoapp.messaging.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Sender {

    private final RabbitTemplate rabbitTemplate;

    @Value("${hawk.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${hawk.rabbitmq.routing-key}")
    private String routingKey;

    @Autowired
    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        log.info("Sending message: " + message);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
     }
}
