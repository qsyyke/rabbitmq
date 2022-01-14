package xyz.xcye.springrabbitmq.other;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/13 13:17
 *
 * 这是死信队列的消费者
 **/
@Component
@Slf4j
public class DeadLetterConsumer implements Serializable {

    @RabbitListener(queues = "QD")
    public void receive(Channel channe,String message) {
        log.info("当前时间{},收到消息{}",new Date().toString(),message);
    }

    @RabbitListener(queues = "QF")
    public void receiveDelayed(String message) {
        log.info("当前时间{}，收到消息{}",new Date().toString(),message);
    }
}
