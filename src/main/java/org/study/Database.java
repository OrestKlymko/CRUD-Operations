package org.study;

import java.sql.Connection;

public class Storage {
	private final static Storage INSTANCE = new Storage();

	private Connection connection;
	private Storage(){
		try {
			String connectionURL = "jdbc:h2:./test";
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static Storage getInstance(){
		return INSTANCE;
	}
}
