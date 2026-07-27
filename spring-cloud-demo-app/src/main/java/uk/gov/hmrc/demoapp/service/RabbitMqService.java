package uk.gov.hmrc.demoapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.hmrc.demoapp.messaging.rabbitmq.Sender;
import uk.gov.hmrc.demoapp.model.RabbitMessage;
import uk.gov.hmrc.demoapp.repository.RabbitMessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class RabbitMqService {

    private final RabbitMessageRepository rabbitMessageRepository;

    private final Sender sender;

    @Autowired
    public RabbitMqService(RabbitMessageRepository rabbitMessageRepository, Sender sender) {
        this.rabbitMessageRepository = rabbitMessageRepository;
        this.sender = sender;
    }

    public void sendMessage(String message) {
        try {
            sender.send(message);
        } catch (Exception e) {
            log.error("Error sending message. ", e);
        }
    }

    public void storeReceivedMessage(String message) {
        RabbitMessage rabbitMessage = RabbitMessage.builder().id(getNextId())
                .message(message).dateAdded(LocalDateTime.now()).build();
        rabbitMessageRepository.save(rabbitMessage);
    }

    private long getNextId() {
        List<RabbitMessage> messages = rabbitMessageRepository.findAll();
        long currentId = messages.stream()
                .mapToLong(RabbitMessage::getId)
                .max().orElse(0);
        return currentId+1;
    }

    public List<RabbitMessage> getMessages() {
        return rabbitMessageRepository.findAllByOrderByDateAddedDesc();
    }

    public String getLastReceivedMessage() {
        List<RabbitMessage> messages = rabbitMessageRepository.findAllByOrderByDateAddedDesc();
        if (messages.isEmpty()) {
            return null;
        } else {
            return messages.get(0).getMessage();
        }
    }
}
