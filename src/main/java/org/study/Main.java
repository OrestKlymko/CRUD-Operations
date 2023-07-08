package org.study;


import java.sql.*;

public class Main {
	public static void main(String[] args) throws SQLException {
		DatabaseQueryService dbs = new DatabaseQueryService();
		System.out.println(dbs.findMaxProjectsClient());
		System.out.println(dbs.findLongestProject());
	    System.out.println(dbs.getWorkerAndSalary());
		System.out.println(dbs.getYoungerAndEldestWorkers());
	}
}