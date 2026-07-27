package uk.gov.hmrc.demoapp.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Configuration
public class RabbitMqConfig {
    @Value("${hawk.rabbitmq.queueName}")
    private String queueName;

    @Value("${hawk.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${hawk.rabbitmq.routing-key}")
    private String routingKey;
    
    @Value("${hawk.rabbitmq.circuitBreakerQueue}")
    private String circuitBreakerQueue;

    @Value("${hawk.rabbitmq.circuitBreakerExchange}")
    private String circuitBreakerExchange;

    private static final boolean DURABLE = true;
    private static final boolean AUTO_DELETED = false;
    private static final boolean EXCLUSIVE = false;

   
    @Bean
    public Declarables directExchange() {
    	String basename = queueName.toString().replace("-queue", "");
    	String UseQueue = basename + "-queue";
    	String UseQueueDL = basename + "-dl-queue";
    	String UseQueueRoutingKey = basename;
    	String UseQueueDLRoutingKey = basename + "-dl";

    	FanoutExchange cbExchange = new FanoutExchange(circuitBreakerExchange);
        Queue cbqu = new Queue(circuitBreakerQueue,DURABLE,EXCLUSIVE,AUTO_DELETED,Map.of(
                "x-queue-type", "quorum"
        ));
        
    	log.info("x-dead-letter-exchange: " + exchangeName);
    	log.info("basename: " + basename);
    	log.info("exchangeName: " + exchangeName);
    	log.info("UseQueue is: " + UseQueue);
    	log.info("x-dead-letter-routing-key: " + UseQueueRoutingKey);    	
    	log.info("DLQueue: " + UseQueueDL);
    	log.info("UseQueueDLRoutingKey: " + UseQueueDLRoutingKey);    	
        
        DirectExchange ex = new DirectExchange(exchangeName, DURABLE, AUTO_DELETED);
        Queue qu = new Queue(UseQueue,DURABLE,EXCLUSIVE,AUTO_DELETED,Map.of(
                "x-queue-type", "quorum",
                "x-dead-letter-exchange",exchangeName,
                "x-dead-letter-routing-key", UseQueueDLRoutingKey
        ));
        Queue DLqu = new Queue(UseQueueDL,DURABLE,EXCLUSIVE,AUTO_DELETED,Map.of(
                "x-queue-type", "quorum"
        ));
        return new Declarables(
        		cbExchange,
        		cbqu,
        		BindingBuilder.bind(cbqu).to(cbExchange),
                ex,
                qu,
                DLqu,
                BindingBuilder.bind(qu).to(ex).with(UseQueueRoutingKey),
                BindingBuilder.bind(DLqu).to(ex).with(UseQueueDLRoutingKey)
        );
    }
 
    @Bean
    public Declarables directProcessorExchange() {
    	String basename = queueName.toString().replace("-queue", "-processor");
    	String UseQueue = basename + "-queue";
    	String UseQueueDL = basename + "-dl-queue";
    	String UseQueueRoutingKey = basename;
    	String UseQueueDLRoutingKey = basename + "-dl";

    	FanoutExchange cbExchange = new FanoutExchange(circuitBreakerExchange);
        Queue cbqu = new Queue(circuitBreakerQueue,DURABLE,EXCLUSIVE,AUTO_DELETED,Map.of(
                "x-queue-type", "quorum"
        ));
        
    	log.info("x-dead-letter-exchange: " + exchangeName);
    	log.info("basename: " + basename);
    	log.info("exchangeName: " + exchangeName);
    	log.info("UseQueue is: " + UseQueue);
    	log.info("x-dead-letter-routing-key: " + UseQueueRoutingKey);    	
    	log.info("DLQueue: " + UseQueueDL);
    	log.info("UseQueueDLRoutingKey: " + UseQueueDLRoutingKey);    	
        
        DirectExchange ex = new DirectExchange(exchangeName, DURABLE, AUTO_DELETED);
        Queue qu = new Queue(UseQueue,DURABLE,EXCLUSIVE,AUTO_DELETED,Map.of(
                "x-queue-type", "quorum",
                "x-dead-letter-exchange",exchangeName,
                "x-dead-letter-routing-key", UseQueueDLRoutingKey
        ));
        Queue DLqu = new Queue(UseQueueDL,DURABLE,EXCLUSIVE,AUTO_DELETED,Map.of(
                "x-queue-type", "quorum"
        ));
        return new Declarables(
        		cbExchange,
        		cbqu,
        		BindingBuilder.bind(cbqu).to(cbExchange),
                ex,
                qu,
                DLqu,
                BindingBuilder.bind(qu).to(ex).with(UseQueueRoutingKey),
                BindingBuilder.bind(DLqu).to(ex).with(UseQueueDLRoutingKey)
        );
    }
}