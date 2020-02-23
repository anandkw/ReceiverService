package app.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Listener {

	private static final String LOG_COLLECTION_NAME = "receiver_logs";
	protected static Logger LOGGER = LoggerFactory.getLogger(Listener.class);
	@Autowired
	protected MongoTemplate mongoTemplate;

	public abstract void receive(byte[] msg) throws IOException;

	protected String convertToString(byte[] msg) {
		return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(msg))).lines().reduce(String::concat).orElse(null);
	}

	protected void logInMongo(String listenerId, String payload) throws IOException {
		if(!mongoTemplate.collectionExists(LOG_COLLECTION_NAME)) {
			mongoTemplate.createCollection(LOG_COLLECTION_NAME);
		}
		Document dbObject = new Document()
				.append("listener", listenerId)
				.append("payload", new ObjectMapper().readValue(payload, Document.class))
				.append("timestamp", System.currentTimeMillis());
		mongoTemplate.save(dbObject,LOG_COLLECTION_NAME);
	}
}
