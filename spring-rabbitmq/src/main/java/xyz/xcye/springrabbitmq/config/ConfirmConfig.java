package xyz.xcye.springrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/13 19:56
 *
 * 这是确认发布高级部分的配置项，在这里配置交换机，队列
 **/

@Configuration
public class ConfirmConfig {
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return ExchangeBuilder.directExchange("confirmExchange").withArgument("alternate","backupExchange").build();
    }

    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return new Queue("confirmQueue");
    }

    @Bean
    public Binding confirmBindQueue(@Qualifier("confirmExchange") DirectExchange confirmExchange,
                                    @Qualifier("confirmQueue") Queue confirmQueue) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with("confirm-routing-key");
    }

    /**
     * 声明一个备份交换机
     */
    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange("backupExchange");
    }

    /**
     * 声明一个备份交换队列
     */
    @Bean("backupQueue")
    public Queue backupQueue() {
        return new Queue("backupQueue");
    }

    @Bean
    public Binding bindingBackupQueue(@Qualifier("backupExchange") FanoutExchange fanoutExchange,
                                      @Qualifier("backupQueue") Queue backupQueue) {
        return BindingBuilder.bind(backupQueue).to(fanoutExchange);

    }


}
