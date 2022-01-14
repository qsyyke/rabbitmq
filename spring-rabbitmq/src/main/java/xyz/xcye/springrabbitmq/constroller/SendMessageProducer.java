package xyz.xcye.springrabbitmq.constroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/13 13:01
 *
 * 这是生产者的控制器
 **/
@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public String sendMessage(@PathVariable String message) {
      log.info("当前时间{}，发送第一条消息: {}",new Date().toString(),message);
      rabbitTemplate.convertAndSend("X","XA","消息来自ttl为10s的队列: " + message);
      rabbitTemplate.convertAndSend("X","XB","消息来自ttl为40s的队列: " + message);
      return  "successful";
    }

    @GetMapping("/sendMsg/{message}/{time}")
    public String sendMessage(@PathVariable String message,@PathVariable Integer time) {
        log.info("当前时间{}，发送一条延迟{}s的时间消息: {}",new Date().toString(),time,message);
        rabbitTemplate.convertAndSend("delayedMessage","delayed-routing-key",message,correlationData -> {
            correlationData.getMessageProperties().setExpiration(time + "");
            return correlationData;
        });
        return  "successful";
    }
}
