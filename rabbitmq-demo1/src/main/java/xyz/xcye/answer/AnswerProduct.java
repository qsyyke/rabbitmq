package xyz.xcye.answer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import xyz.xcye.untils.RabbitUtil;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/08 13:01
 *
 * 这是一个消息应答测试的生产者
 **/

public class AnswerProduct {
    public static void main(String[] args) throws Exception {
        String queue_name = "answer_queue_4";
        Scanner scanner = new Scanner(System.in);

        Channel chanel = RabbitUtil.getChanel();
        AMQP.Queue.DeclareOk declareOk = chanel.queueDeclare(queue_name, true, false, false, null);

        while (scanner.hasNext()) {
            String message = scanner.next();

            chanel.basicPublish("",queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes(StandardCharsets.UTF_8));

            System.out.println("生产者发送消息: " + message);
        }

    }
}
