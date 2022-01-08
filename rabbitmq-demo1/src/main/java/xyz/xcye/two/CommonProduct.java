package xyz.xcye.two;

import com.rabbitmq.client.Channel;
import xyz.xcye.untils.RabbitUtil;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/08 11:27
 * 这是共同的生产者
 **/

public class CommonProduct {
    public static void main(String[] args) throws Exception {

        String queueName = "work-queue-4";
        Channel chanel = RabbitUtil.getChanel();

        chanel.queueDeclare(queueName,false,false,false,null);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            chanel.basicPublish("",queueName,null,scanner.next().getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者生产成功: " + scanner.next());
        }
    }
}
