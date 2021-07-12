package com.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by fengtj on 2021/7/12 7:55
 */
public class RabbitMQUtils {

    private static ConnectionFactory factory;

    static {
        factory = new ConnectionFactory();
    }

    /**
     * 创建连接的方法
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            //ConnectionFactory factory = new ConnectionFactory();
            //设置主机host
            factory.setHost("47.111.72.203");
            //设置端口
            factory.setPort(5672);
            //设置用户名
            factory.setUsername("ems");
            //设置密码
            factory.setPassword("123");
            //设置虚拟主机
            factory.setVirtualHost("/ems");
            //构建连接
            return factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭MQ通道和连接
     * @param conn
     * @param channel
     */
    public static void closeConnectionAndChanel(Connection conn, Channel channel) {
        try {
            if (channel != null) {
                channel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
