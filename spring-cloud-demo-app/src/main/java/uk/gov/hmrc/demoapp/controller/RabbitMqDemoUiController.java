package uk.gov.hmrc.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uk.gov.hmrc.demoapp.model.RabbitMessage;
import uk.gov.hmrc.demoapp.service.RabbitMqService;

import java.util.List;

@Controller
@RequestMapping("/web/messaging/rabbitmq")
public class RabbitMqDemoUiController {

    private final RabbitMqService rabbitMqService;

    @Autowired
    public RabbitMqDemoUiController(RabbitMqService rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }

    @PostMapping("/send")
    @LoadBalanced
    public String uiSendMessage(@RequestParam("message") String message, Model model) {
        rabbitMqService.sendMessage(message);
        return uiReceiveMessage(model);
    }

    @GetMapping("/")
    @LoadBalanced
    public String uiReceiveMessage(Model model) {
        List<RabbitMessage> messages = rabbitMqService.getMessages();
        model.addAttribute("messages", messages);
        return "rabbitmq/rabbitmq";
    }
}
