package app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "queue2")
@Component
public class DirectListener extends Listener {

	@RabbitHandler(isDefault = true)
	@Override
	public void receive(byte[] msg) {
		LOGGER.info("Queue - queue2");
		LOGGER.info(convertToString(msg));
	}
}
