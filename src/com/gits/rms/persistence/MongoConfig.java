package com.gits.rms.persistence;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.gits.rms.utils.PropertyUtil;


//This class to maintain the mongo database connection
public class MongoConfig {
    static Logger log = Logger.getLogger(MongoConfig.class.getName());// for store log details

	public static DB createConnection() {
		Mongo mongo = null;
		DB db = null;

		try {
		   String hostname = PropertyUtil.getPropoerty("Mongodb.hostname");
	       int port = Integer.parseInt(PropertyUtil.getPropoerty("Mongodb.port"));
		   String dbName = PropertyUtil.getPropoerty("Mongodb.db");
            
	       mongo = new Mongo(hostname, port);
	       db = mongo.getDB(dbName);
		   log.info("Connect to mongo database successfully");
		}
		catch(Exception e) {
		   log.error("Exception occurred during the connection");	
		}
		return db;
	}
}
