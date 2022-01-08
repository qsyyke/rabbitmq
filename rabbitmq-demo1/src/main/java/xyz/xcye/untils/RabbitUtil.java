package xyz.xcye.untils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/08 10:41
 **/

public class RabbitUtil {
    public static Channel getChanel() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.86.142");
        factory.setUsername("admin");
        factory.setPassword("123456");
        Channel channel = null;
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    };
}
