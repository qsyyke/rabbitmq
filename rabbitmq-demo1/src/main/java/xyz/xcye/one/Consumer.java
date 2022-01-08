package xyz.xcye.one;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/08 10:21
 * 这是一个消费者
 **/

public class Consumer {
    public final static String QUEUE_NAME = "mq_queue_1";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.86.142");
        factory.setUsername("admin");
        factory.setPassword("123456");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        /**
         * queue – the name of the queue
         * deliverCallback – callback when a message is delivered
         * cancelCallback – callback when the consumer is cancelled
         */
        channel.basicConsume(QUEUE_NAME, new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                System.out.println("consumerTag = " + consumerTag);
                System.out.println("message = " + new String(message.getBody()));
            }
        }, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {
                System.out.println("consumerTag = " + consumerTag);
            }
        });

        System.out.println("消费者执行");
    }
}
