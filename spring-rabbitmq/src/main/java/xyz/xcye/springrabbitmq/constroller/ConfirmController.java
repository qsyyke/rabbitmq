package xyz.xcye.springrabbitmq.constroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/13 20:03
 *
 * 这是确认发布的高级部分
 **/
@Slf4j
@RestController
@RequestMapping("/confirm")
public class ConfirmController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message) {
        log.info("当前时间{},发送 --> {} 消息",new Date().toString(),message);
        rabbitTemplate.convertAndSend("confirmExchange","confirm-rsouting-key",message);
    }
}
