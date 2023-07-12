package org.study;


import org.flywaydb.core.Flyway;

public class DatabaseInitService {

	public static void main(String[] args) {
		Flyway flyway = Flyway.configure().dataSource(Сonstants.CONNECTION_URL,"user","password").load();
		flyway.migrate();
	}
}
