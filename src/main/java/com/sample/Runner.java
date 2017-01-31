package com.sample;


import java.util.concurrent.TimeUnit;

import com.sample.config.AmqpConfig;
import com.sample.messaging.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  private final Receiver receiver;
  private final ConfigurableApplicationContext context;

  public Runner(Receiver receiver, RabbitTemplate rabbitTemplate,
                ConfigurableApplicationContext context) {
    this.receiver = receiver;
    this.rabbitTemplate = rabbitTemplate;
    this.context = context;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend(AmqpConfig.QUEUE_DEMO_NOTIFY, "Hello from RabbitMQ!");
    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    context.close();
  }

}