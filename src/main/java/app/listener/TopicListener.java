package app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "queue1")
@Component
public class TopicListener extends Listener{

	@RabbitHandler(isDefault = true)
	@Override
	public void receive(byte[] msg) {
		LOGGER.info("Queue - queue1");
		LOGGER.info(convertToString(msg));
	}
}
