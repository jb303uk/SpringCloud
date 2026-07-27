package uk.gov.hmrc.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.hmrc.demoapp.service.RabbitMqService;

@RestController
@RequestMapping(path = "/messaging/rabbitmq")
public class RabbitMqDemoController {

    private final RabbitMqService rabbitMqService;

    @Autowired
    public RabbitMqDemoController(RabbitMqService rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }


    @GetMapping("/send/{message}")
    @LoadBalanced
    public String apiSendMessage(@PathVariable("message") String message) {
        rabbitMqService.sendMessage(message);
        return "Message Sent: " + message;
    }

    @GetMapping("/receive")
    @LoadBalanced
    public String apiReceiveMessage() {
        return "Message Received: " + rabbitMqService.getLastReceivedMessage();
    }
}
