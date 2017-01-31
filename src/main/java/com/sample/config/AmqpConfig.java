package com.sample.config;

import com.sample.messaging.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
  public final static String QUEUE_DEMO_NOTIFY = "Demo.Notify";

  @Bean
  Queue queueDemoNotify() {
    return new Queue(QUEUE_DEMO_NOTIFY, false);
  }

  @Bean
  TopicExchange exchangeDemoNotify() {
    return new TopicExchange("demo-notify-exchange");
  }

  @Bean
  Binding bindingDemoNotify(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(QUEUE_DEMO_NOTIFY);
  }

  @Bean
  SimpleMessageListenerContainer containerDemoNotify(ConnectionFactory connectionFactory,
                                                     MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(QUEUE_DEMO_NOTIFY);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveTheMessage");
  }
}
