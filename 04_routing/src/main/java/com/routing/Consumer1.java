package com.routing;

import com.rabbitmq.client.Connection;
import com.utils.RabbitMQUtils;

/**
 * Created by fengtj on 2021/7/26 23:49
 */
public class Consumer1 {

    public static void main(String[] args) {
        Connection connection = RabbitMQUtils.getConnection();

    }
}
