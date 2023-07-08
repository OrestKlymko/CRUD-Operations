package org.study;

import sqlObjects.LongestProject;
import sqlObjects.MaxProjectCountClient;
import sqlObjects.WorkerAndSalary;
import sqlObjects.YoungestAndElder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

	private ResultSet connectToDB(String sqlURLFile) {
		try {
			String sql = Files.readString(Path.of(sqlURLFile));
			Statement statement = Database.getConnection().createStatement();
			return statement.executeQuery(sql);
		} catch (IOException | SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException {
		List<MaxProjectCountClient> list = new ArrayList<>();
		String sql = "src/main/java/sql/find_max_projects_client.sql";
		ResultSet resultSet = connectToDB(sql);
		while (resultSet.next()) {
			try {
				String name = resultSet.getString("NAME");
				int count = resultSet.getInt("COUNT_PROJECT");
				list.add(new MaxProjectCountClient(name, count));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}


	public List<LongestProject> findLongestProject() throws SQLException {
		List<LongestProject> list = new ArrayList<>();
		String sql = "src/main/java/sql/find_longest_project.sql";
		ResultSet resultSet = connectToDB(sql);
		while (resultSet.next()) {
			try {
				int id = resultSet.getInt("ID");
				int countMonth = resultSet.getInt("MONTH_COUNT");
				list.add(new LongestProject(id, countMonth));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	public List<WorkerAndSalary> getWorkerAndSalary() throws SQLException {
		List<WorkerAndSalary> list = new ArrayList<>();
		String sql = "src/main/java/sql/find_max_salary_worker.sql";
		ResultSet resultSet = connectToDB(sql);
		while (resultSet.next()) {
			try {
				String workerName = resultSet.getString("NAME");
				int salary = resultSet.getInt("SALARY");
				list.add(new WorkerAndSalary(workerName, salary));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}

	public List<YoungestAndElder> getYoungerAndEldestWorkers() throws SQLException {
		List<YoungestAndElder> list = new ArrayList<>();
		String sql = "src/main/java/sql/find_youngest_eldest_workers.sql";
		ResultSet resultSet = connectToDB(sql);
		while (resultSet.next()) {
			try {
				String workerName = resultSet.getString("NAME");
				LocalDate date = resultSet.getDate("BIRTHDAY").toLocalDate();
				String type = resultSet.getString("TYPE");
				list.add(new YoungestAndElder(workerName, date, type));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return list;
	}
}

