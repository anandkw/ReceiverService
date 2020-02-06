package app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "queue3")
@Component
public class FanoutListener1 extends Listener {

	@RabbitHandler(isDefault = true)
	@Override
	public void receive(byte[] msg) {
		LOGGER.info("Queue - queue3");
		LOGGER.info(convertToString(msg));
	}
}
