package xyz.xcye.springrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/12 21:52
 *
 * 这是死信队列的一个配置类
 **/
@Configuration
public class TtlQueueConfig {
    /** 这是一个普通交换机X **/
    public static final String X_EXCHANGE = "X";

    /** 这是普通队列QA **/
    public static final String QUEUE_A = "QA";

    /** 这是普通队列QB **/
    public static final String QUEUE_B = "QB";

    /** 这是死信交换机的名称 **/
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";

    /** 这是死信队列名称 **/
    public static final String DEAD_LETTER_QUEUE = "QD";

    /**
     * 这是一个自定义普通队列QC
     */
    public static final String CUSTOM_QUEUE = "QC";

    /**
     * 声明普通交换机X
     */
    @Bean("xExchange")
    public DirectExchange xExchange() {
        return new DirectExchange(X_EXCHANGE);
    }

    /**
     * 声明死信交换机
     */
    @Bean("yExchange")
    public DirectExchange yExchange() {
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明普通队列QA
     */
    @Bean("queueA")
    public Queue queueA() {
        Map<String,Object> args = new HashMap<>();

        //绑定死信交换机
        args.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);

        //将队列QA绑定到死信交换机上
        args.put("x-dead-letter-routing-key","YD");

        //设置队列QA的ttl为10s 单位是ms
        args.put("x-message-ttl",10000);

        return QueueBuilder.durable(QUEUE_A).withArguments(args).build();
    }

    /**
     * 声明自定义延迟时间队列QC
     */
    @Bean("queueC")
    public Queue queueC() {
        Map<String,Object> args = new HashMap<>();

        //绑定死信交换机
        args.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);

        //将队列QA绑定到死信交换机上
        args.put("x-dead-letter-routing-key","YD");

        return QueueBuilder.durable(CUSTOM_QUEUE).withArguments(args).build();
    }

    /**
     * 声明普通队列QB
     */
    @Bean("queueB")
    public Queue queueB() {
        Map<String,Object> args = new HashMap<>();

        //绑定死信交换机
        args.put("x-dead-letter-exchange",Y_DEAD_LETTER_EXCHANGE);

        //将队列QA绑定到死信交换机上
        args.put("x-dead-letter-routing-key","YD");

        //设置队列QA的ttl为40s 单位是ms
        args.put("x-message-ttl",40000);

        return QueueBuilder.durable(QUEUE_B).withArguments(args).build();
    }

    /**
     * 声明死信队列QD
     */
    @Bean("queueD")
    public Queue queueD() {
        return new Queue(DEAD_LETTER_QUEUE);
    }

    /**
     * 将队列QA和交换机X进行绑定
     */
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA,
                                 @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }

    /**
     * 将队列QB和交换机X进行绑定
     */
    @Bean
    public Binding queueBBindingX(@Qualifier("queueB") Queue queueB,
                                 @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }

    /**
     * 将队列QC和交换机X进行绑定
     */
    @Bean
    public Binding queueCBindingX(@Qualifier("queueC") Queue queueC,
                                  @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }

    /**
     * 将死信队列QD和交换机Y进行绑定
     */
    @Bean
    public Binding deadLetterBindingQueueD(@Qualifier("queueD") Queue queued,
                                           @Qualifier("yExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queued).to(xExchange).with("YD");
    }

    /**
     * 创建一个新的交换机，此交换机的类型为x-delayed-message
     */
    @Bean("delayedExchange")
    public CustomExchange delayedExchange() {
        Map<String, Object> args = new HashMap<>();
        //自定义交换机的类型
        args.put("x-delayed-type", "direct");
        return new CustomExchange("delayedMessage","x-delayed-message",false,false,args);
    }

    /**
     * 解决延迟队列的队列QF
     */
    @Bean("queueF")
    public Queue delayedQueue() {
        return QueueBuilder.durable("QF").build();
    }

    @Bean
    public Binding bindingDelayedQueue(@Qualifier("queueF") Queue queueF,
                                       @Qualifier("delayedExchange") CustomExchange delayedExchange) {
        return BindingBuilder.bind(queueF).to(delayedExchange).with("delayed-routing-key").noargs();
    }
}
