package xyz.xcye.springrabbitmq.other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/13 20:07
 *
 * 这是确认发布的高级部分
 **/

@Slf4j
@Component
public class ConfirmConsumer {

    @RabbitListener(queues="confirmQueue")
    public void receiveConfirmMsg(String message) {
        log.info("当前时间{},接收确认消息 {}",new Date().toString(),message);
    }

    @RabbitListener(queues="backupQueue")
    public void backupQueue(String message) {
        log.warn("当前时间{},报警接收确认消息 {}",new Date().toString(),message);
    }
}
