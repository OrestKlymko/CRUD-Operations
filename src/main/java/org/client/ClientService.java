package org.client;

import org.database.DbConstants;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
	private PreparedStatement listAllSt;
	private PreparedStatement getByIdSt;
	private PreparedStatement deleteByIdSt;
	private PreparedStatement setNameSt;
	private PreparedStatement createClientSt;

	private Connection connection = DriverManager.getConnection(DbConstants.CONNECTION_URL);

	public ClientService() throws SQLException {
		listAllSt = connection.prepareStatement("SELECT id,name FROM client");
		getByIdSt = connection.prepareStatement("SELECT id,name FROM client WHERE id=?");
		deleteByIdSt = connection.prepareStatement("DELETE FROM client WHERE id=?");
		setNameSt = connection.prepareStatement("UPDATE client SET name=? WHERE id=?");
		createClientSt = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");
	}


	public long create(String name){
		try {
			createClientSt.setString(1,name);
			createClientSt.executeUpdate();

			ResultSet generatedKeys = createClientSt.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			} else {
				throw new SQLException("Не вдалося отримати згенерований id клієнта.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Client> listAll() {
		List<Client> resultList = new ArrayList<>();

		try {
		ResultSet resultSet = listAllSt.executeQuery();
			while (resultSet.next()){
				Client client = new Client();
				client.setId(resultSet.getLong("id"));
				client.setName(resultSet.getString("name"));
				resultList.add(client);
				listAllSt.executeBatch();
			}
			return resultList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String getById(long id) {
		try {
			getByIdSt.setLong(1,id);
			ResultSet resultSet = getByIdSt.executeQuery();
			if(resultSet.next()) return resultSet.getString("name");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return "Client with id: "+id+" not found";

	}

	public void deleteById(long id){

		try {
			deleteByIdSt.setLong(1,id);
			deleteByIdSt.executeUpdate();
			System.out.println("Delete success");
		} catch (SQLException e) {
			throw new RuntimeException("Customer with "+id+" wasn't delete");
		}
	}

	public void setName(long id, String name){
		try {
			setNameSt.setString(1,name);
			setNameSt.setLong(1,id);
			setNameSt.executeUpdate();
			System.out.println("User updated");
		} catch (SQLException e) {
			throw new RuntimeException("User with "+id+" not found");
		}
	}
}
