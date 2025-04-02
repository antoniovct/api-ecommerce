package com.antoniovictor.catalogservice.infra.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {

    @Bean
    public Queue estoqueReservadoQueue() {
        return QueueBuilder.durable("estoque.reservado").build();
    }

    @Bean
    public Queue estoqueInsuficienteQueue() {
        return QueueBuilder.durable("estoque.insuficiente").build();
    }

    @Bean
    public RabbitAdmin criaAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin admin) {
        return event -> admin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public Exchange catalogExchange() {
        return ExchangeBuilder.directExchange("catalog.ex").durable(true).build();
    }
    
    @Bean
    public Binding bindingEstoqueReservadoQueue() {
        return BindingBuilder.bind(estoqueReservadoQueue()).to(catalogExchange()).with("estoque.reservado").noargs();
    }

    @Bean
    public Binding bindingEstoqueInsuficienteQueue() {
        return BindingBuilder.bind(estoqueInsuficienteQueue()).to(catalogExchange()).with("estoque.insuficiente").noargs();
    }
}
