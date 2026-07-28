package uk.gov.hmrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.hmrc.entity.Greeting;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class GreetingController {

    @Autowired
    private ServletWebServerApplicationContext server;    
    private static final String template = "Hostname:%s Port:%s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(
    		@RequestParam(value="name", defaultValue="${spring.cloud.client.hostname}") String name) {
        return new Greeting(counter.incrementAndGet(),
          String.format(template, name, server.getWebServer().getPort()));
    }
  
}
