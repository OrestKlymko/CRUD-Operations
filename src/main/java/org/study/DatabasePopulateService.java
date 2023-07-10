package org.study;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class DatabasePopulateService {
	private static Connection connection;

	private DatabasePopulateService(){
		try {
			String connectionURL = "jdbc:h2:./db";
			connection = DriverManager.getConnection(connectionURL);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			String sql = Files.readString(Path.of("src/main/java/sql/populate_db.sql"));
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1,"John Doe");
			statement.setDate(2, Date.valueOf("1990-01-01"));
			statement.setString(3,"Trainee");
			statement.setInt(4,800);
			statement.addBatch();

			statement.setString(5,"Company");

			statement.setInt(6,1);
			statement.setDate(7, Date.valueOf("2022-01-01"));
			statement.setDate(8, Date.valueOf("2022-04-30"));


			statement.setInt(9,1);
			statement.setInt(10,1);

			statement.executeBatch();

		} catch (IOException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
