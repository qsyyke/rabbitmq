package xyz.xcye.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import xyz.xcye.untils.RabbitUtil;

import java.io.IOException;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/08 11:26
 * 这是共同的消费者
 **/

public class CommonConsumer {
    public static void main(String[] args) throws Exception {
        String queueName = "work-queue-4";
        Channel chanel = RabbitUtil.getChanel();
        chanel.basicConsume(queueName, new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                System.out.println("b号消费者: " + new String(message.getBody()));
            }
        }, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {

            }
        });
    }
}
