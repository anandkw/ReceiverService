package app.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RabbitListener(queues = "queue4")
@Component
public class FanoutListener2 extends Listener {

	@RabbitHandler(isDefault = true)
	@Override
	public void receive(byte[] msg) throws IOException {
		LOGGER.info("Queue - queue4");
		String payload = convertToString(msg);
		LOGGER.info(payload);
		logInMongo(this.getClass().getName(), payload);
	}
}
