package xyz.xcye.answer;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import xyz.xcye.untils.RabbitUtil;

import java.io.IOException;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/08 13:24
 *
 * 这是消息应答部分的消费者
 **/

public class AnswerConsumer2 {
    public static void main(String[] args) throws Exception {
        String queue_name = "answer_queue_4";
        System.out.println("b消费者等待接收消息，睡眠时间10000ms");

        Channel chanel = RabbitUtil.getChanel();
        chanel.basicConsume(queue_name, false, new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("b消费者接收: " + new String(message.getBody()));

                //手动应答
                chanel.basicAck(message.getEnvelope().getDeliveryTag(),false);
            }
        }, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {
                System.out.println("取消消息");
            }
        });
    }
}
