package app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "queue4")
@Component
public class FanoutListener2 extends Listener {

	@RabbitHandler(isDefault = true)
	@Override
	public void receive(byte[] msg) {
		LOGGER.info("Queue - queue4");
		LOGGER.info(convertToString(msg));
	}
}