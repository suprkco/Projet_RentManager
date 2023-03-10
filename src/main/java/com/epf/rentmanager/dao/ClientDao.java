package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
			instance = new ClientDao();
		}
		return instance;
	}
	
	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(lastname, firstname, email, birthday) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String GET_CLIENTS_COUNT_QUERY = "SELECT COUNT(*) AS count FROM Client;";
	private static final String FIND_CLIENT_QUERY = "SELECT lastname, firstname, email, birthday FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, lastname, firstname, email, birthday FROM Client;";

	// create a client
	// When creating or updating a Client, we will make sure that his last name is recorded in CAPITALS in the database.
	public long create(Client client) throws DaoException {
		return 0;
	}

	// delete a client by its id
	public long delete(Client client) throws DaoException {
		return 0;
	}

	// find a client by its id
	public Client findById(int id) throws DaoException {
		Client client = new Client();
		try {
			// get connection
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_CLIENT_QUERY);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			// if there is a result, create a client
			if (resultSet.next()) {
				String lastname = resultSet.getString("lastname");
				String firstname = resultSet.getString("firstname");
				String email = resultSet.getString("email");
				LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
				// set the attributes of the client
				client.setId(id);
				client.setLastname(lastname);
				client.setFirstname(firstname);
				client.setEmail(email);
				client.setBirthdate(birthday);
			}
		// if there is no result, throw an exception
		} catch (SQLException e) {
			throw new DaoException("Problem when retrieving the client: " + e.getMessage());
		}
		// return the client
		return client;
	}

	// find all clients
	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<Client>();
		try {
			// get connection
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_CLIENTS_QUERY);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String lastname = resultSet.getString("lastname");
				String firstname = resultSet.getString("firstname");
				String email = resultSet.getString("email");
				LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
				// add the client to the list
				clients.add(new Client(id, lastname, firstname, email, birthday));
			}
		// if there is no result, throw an exception
		} catch (SQLException e) {
			throw new DaoException("Problem when retrieving the clients: " + e.getMessage());
		}
		// return the list of clients
		return clients;
	}

	// get the number of clients
    public int getCount() throws DaoException {
		int count = 0;
		try {
			// get connection
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(GET_CLIENTS_COUNT_QUERY);
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		// if there is no result, throw an exception
		} catch (SQLException e) {
			throw new DaoException("Problem when retrieving the number of clients: " + e.getMessage());
		}
		// return the number of clients
		return count;
    }

	// Insert a client in the database
	public void insert(Client client) throws SQLException {
		Connection connection = ConnectionManager.getConnection();

		String sql = CREATE_CLIENT_QUERY;
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, client.getLastname());
			stmt.setString(2, client.getFirstname());
			stmt.setString(3, client.getEmail());
			stmt.setDate(4, java.sql.Date.valueOf(client.getBirthdate()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
