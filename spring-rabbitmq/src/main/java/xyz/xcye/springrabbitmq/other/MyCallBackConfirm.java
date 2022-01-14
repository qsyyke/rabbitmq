package xyz.xcye.springrabbitmq.other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/13 21:01
 **/

@Component
@Slf4j
public class MyCallBackConfirm implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(correlationData);
        if (ack) {
            log.info("消息成功接收");
        }else {
            log.info("消息接收失败，报错信息{}",cause);
        }
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        System.out.println("执行init方法");
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        log.info("消息 {} 被回退,路由为{},回退原因{}",new String(returned.getMessage().getBody()),returned.getRoutingKey(),returned.getReplyText());
    }
}
