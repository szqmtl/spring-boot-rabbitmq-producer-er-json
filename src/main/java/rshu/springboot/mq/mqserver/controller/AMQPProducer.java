package rshu.springboot.mq.mqserver.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rshu.springboot.mq.mqserver.MqServerApplication;

@Component
public class AMQPProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Notification msg){
        System.out.println("Send msg = " + msg.toString());
        rabbitTemplate.convertAndSend(MqServerApplication.ExchangeName, MqServerApplication.RoutingName, msg);
    }

}
