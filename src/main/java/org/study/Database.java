package org.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private final static Database INSTANCE = new Database();

	private static Connection connection;
	private Database(){
		try {
			String connectionURL = "jdbc:h2:./db";
			connection = DriverManager.getConnection(connectionURL);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static Database  getInstance(){
		return INSTANCE;
	}

	public static Connection getConnection() {
		return connection;
	}

	public void executeUpdate(String sql){
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			connection.commit();
			close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
