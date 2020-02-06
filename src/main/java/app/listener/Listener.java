package app.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

public abstract class Listener {

	protected static Logger LOGGER = LoggerFactory.getLogger(Listener.class);

	public abstract void receive(byte[] msg);
	protected String convertToString(byte[] msg) {
		return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(msg))).lines().reduce(String::concat).orElse(null);
	}
}
