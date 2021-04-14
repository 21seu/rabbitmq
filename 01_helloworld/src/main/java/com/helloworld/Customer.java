package com.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {

    public static void main(String[] args) throws IOException, TimeoutException {
        getMessage();
    }

    public static void getMessage() throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.111.72.203");
        factory.setPort(5672);
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("123");
        //获取连接对象
        Connection connection = factory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //通道绑定对象
        channel.queueDeclare("hello", false, false, false, null);
        //消费消息
        //参数1：消费哪个队列的消息  参数2：开启消息的自动确认机制  参数3：消费消息时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            //最后一个参数 body 消息队列中取出的消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = " + new String(body));
            }
        });
        //关闭资源  消费消息的时候不建议关闭资源 因为这里是两个线程，主线程把资源关闭了，另一边其实还在监听这个消息
        //channel.close();
        //connection.close();
    }
}
