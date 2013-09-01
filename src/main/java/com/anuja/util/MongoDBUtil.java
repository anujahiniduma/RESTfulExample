package com.anuja.util;

import java.net.UnknownHostException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDBUtil {
	private final static Logger logger = LoggerFactory.getLogger(MongoDBUtil.class);

	private static final int port = 27017;
	private static final String host = "localhost";
	private static Mongo mongo = null;

	public static Mongo getMongo() {
		if (mongo == null) {
			try {
				mongo = new Mongo(host, port);
				logger.debug("New Mongo created with [" + host + "] and ["
					+ port + "]");
			} catch ( MongoException e) {
				logger.error(e.getMessage());
			}catch(UnknownHostException e){
				logger.error(e.getMessage());
			}
		}
	return mongo;
	}

}
