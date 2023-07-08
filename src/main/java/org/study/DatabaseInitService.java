package org.study;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



public class DatabaseInitService {

	public static void main(String[] args) {

		try {
			String sql  = Files.readString(Path.of("src/main/java/sql/init_db.sql"));
			Database.getInstance().executeUpdate(sql);
			Database.getInstance().close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


	}
}
