package org.study;

import sqlObjects.LongestProject;
import sqlObjects.MaxProjectCountClient;
import sqlObjects.WorkerAndSalary;
import sqlObjects.YoungestAndElder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DatabaseQueryService {
	Connection connection = Database.getConnection();
	private String readSQLfromFile(String sqlURLFile) {
		try {
			String sql = Files.readString(Path.of(sqlURLFile));
			return sql;
		} catch (IOException  e) {
			throw new RuntimeException(e);
		}

	}

	public List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException {
		List<MaxProjectCountClient> list = new ArrayList<>();
		String maxCountQuery = "SELECT MAX(COUNT_PROJECT) AS MAX_COUNT_PROJECT " +
				"FROM (" +
				"  SELECT COUNT(*) AS COUNT_PROJECT " +
				"  FROM CLIENT " +
				"  JOIN PROJECT ON CLIENT.ID = PROJECT.CLIENT_ID " +
				"  GROUP BY NAME" +
				") AS subquery";

		PreparedStatement maxCountStatement = connection.prepareStatement(maxCountQuery);
		ResultSet maxCountResult = maxCountStatement.executeQuery();
		if (maxCountResult.next()) {
			int maxCount = maxCountResult.getInt("MAX_COUNT_PROJECT");

			String selectQuery = readSQLfromFile("src/main/java/sql/find_max_projects_client.sql");;
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setInt(1, maxCount);

			ResultSet resultSet = selectStatement.executeQuery();
			while (resultSet.next()) {
				try {
					String name = resultSet.getString("NAME");
					int count = resultSet.getInt("COUNT_PROJECT");
					list.add(new MaxProjectCountClient(name, count));
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return list;
	}



	public List<LongestProject> findLongestProject() throws SQLException {
		List<LongestProject> list = new ArrayList<>();
		String maxDiffQuery = "SELECT MAX(DATEDIFF(month, START_DATE, FINISH_DATE)) AS MAX_DIFF FROM PROJECT";
		PreparedStatement maxDiffStatement = connection.prepareStatement(maxDiffQuery);
		ResultSet maxDiffResult = maxDiffStatement.executeQuery();
		if (maxDiffResult.next()) {
			int maxDiff = maxDiffResult.getInt("MAX_DIFF");

			String selectQuery = readSQLfromFile("src/main/java/sql/find_longest_project.sql");
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setInt(1, maxDiff);

			ResultSet resultSet = selectStatement.executeQuery();
			while (resultSet.next()) {
				try {
					int id = resultSet.getInt("ID");
					int countMonth = resultSet.getInt("MONTH_COUNT");
					list.add(new LongestProject(id, countMonth));
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return list;
	}


	public List<WorkerAndSalary> getWorkerAndSalary() throws SQLException {
		List<WorkerAndSalary> list = new ArrayList<>();

		String maxSalaryQuery = "SELECT MAX(SALARY) AS MAX_SALARY FROM WORKER";
		PreparedStatement maxSalaryStatement = connection.prepareStatement(maxSalaryQuery);
		ResultSet maxSalaryResult = maxSalaryStatement.executeQuery();


		if (maxSalaryResult.next()) {
			int maxSalary = maxSalaryResult.getInt("MAX_SALARY");

			String selectQuery = readSQLfromFile("src/main/java/sql/find_max_salary_worker.sql");
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setInt(1, maxSalary);

			ResultSet resultSet = selectStatement.executeQuery();
			while (resultSet.next()) {
				try {
					String workerName = resultSet.getString("NAME");
					int salary = resultSet.getInt("SALARY");
					list.add(new WorkerAndSalary(workerName, salary));
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return list;
	}


	public List<YoungestAndElder> getYoungerAndEldestWorkers() throws SQLException {
		List<YoungestAndElder> list = new ArrayList<>();

		String youngestWorker = "(SELECT MAX(BIRTHDAY) FROM WORKER)";
		String eldestWorker = "(SELECT MIN(BIRTHDAY) FROM WORKER)";
		String selectQuery = readSQLfromFile("src/main/java/sql/find_youngest_eldest_workers.sql");

		PreparedStatement youngestWorkerStatement = connection.prepareStatement(youngestWorker);
		PreparedStatement eldestWorkerStatement = connection.prepareStatement(eldestWorker);
		ResultSet youngestWorkerResult = youngestWorkerStatement.executeQuery();
		ResultSet eldestWorkerResult = eldestWorkerStatement.executeQuery();

		if (youngestWorkerResult.next() && eldestWorkerResult.next()) {
			Date youngest = youngestWorkerResult.getDate(1);
			Date eldest = eldestWorkerResult.getDate(1);

			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			selectStatement.setDate(1, youngest);
			selectStatement.setDate(2, eldest);

			ResultSet resultSet = selectStatement.executeQuery();
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
		}
		return list;
	}


}


