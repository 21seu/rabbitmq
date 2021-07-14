package com.work;

import com.rabbitmq.client.*;
import com.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @desciption 消息确认机制
 * Created by fengtj on 2021/7/15 7:44
 */
public class Consumer3 {

    public static void main(String[] args) throws IOException {

        Connection connection = RabbitMQUtils.getConnection();

        final Channel channel = connection.createChannel();

        channel.queueDeclare("work", true, false, false, null);
        //每次只消费一个消息
        channel.basicQos(1);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-3：" + new String(body));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //参数1：确认队列中哪个具体消息 一个标识  参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
