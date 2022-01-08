package xyz.xcye.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/08 09:57
 * 这是一个生产者实例
 **/

public class Product {
    public final static String QUEUE_NAME = "mq_queue_3";

    public static void main(String[] args) throws Exception {
        //创建一个链接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.86.142");
        factory.setUsername("admin");
        factory.setPassword("123456");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        /**
         * queue – the name of the queue
         * durable – true if we are declaring a durable queue (the queue will survive a server restart) 消息对列是否持久化，默认存储在内存中
         * exclusive – true if we are declaring an exclusive queue (restricted to this connection)
         * autoDelete – true if we are declaring an autodelete queue (server will delete it when no longer in use)
         * arguments – other properties (construction arguments) for the queue
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String mq_message = "hello mq,this is my first app and this is mq_queue_3";

        /**
         * exchange – the exchange to publish the message to
         * routingKey – the routing key
         * props – other properties for the message - routing headers etc
         * body – the message body
         */
        channel.basicPublish("",QUEUE_NAME,null,mq_message.getBytes(StandardCharsets.UTF_8));

        System.out.println("消息对列发送成功");
    }
}
