package client;

//long create(String name) - додає нового клієнта з іменем name. Повертає ідентифікатор щойно створеного клієнта.
//		String getById(long id) - повертає назву клієнта з ідентифікатором id
//		void setName(long id, String name) - встановлює нове ім'я name для клієнта з ідентифікатором id
//		void deleteById(long id) - видаляє клієнта з ідентифікатором id
//		List<Client> listAll() - повертає всіх клієнтів з БД у вигляді колекції об'єктів типу Client (цей клас створи сам, у ньому має бути 2 поля - id та name)
//		Передбач валідацію вхідних даних в методах класу ClientService. Якщо якісь вхідні дані некоректні (наприклад, ми намагаємось створити клієнта з занадто коротким чи довгим іменем) - відповідний метод має викидати Exception.


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
	private PreparedStatement listAllSt;

	public ClientService(Connection connection) throws SQLException {
		listAllSt = connection.prepareStatement("SELECT id,name FROM client");
	}

	public List<Client> listAll() throws SQLException {
		List<Client> resultList = new ArrayList<>();
		ResultSet resultSet = listAllSt.executeQuery();

		while (resultSet.next()){
			Client client = new Client();
			client.setId(resultSet.getInt("id"));
			client.setName(resultSet.getString("name"));
			resultList.add(client);
			listAllSt.executeBatch();
		}
		return resultList;
	}
}
