package rshu.springboot.mq.mqserver;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MqServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqServerApplication.class, args);
	}

	static final public String ExchangeName = "exchange-test";
	static final public String QueueName = "queue-test";
	static final public String RoutingName = "routing-test";

	@Bean
	DirectExchange exchange(){
		return new DirectExchange(ExchangeName);
	}

	@Bean
	Queue queue(){
		return new Queue(QueueName, false);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(RoutingName);
	}

	@Bean
	public Jackson2JsonMessageConverter messageConverter(){
		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
		return converter;
	}

	@Bean
	public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory){
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}
}
