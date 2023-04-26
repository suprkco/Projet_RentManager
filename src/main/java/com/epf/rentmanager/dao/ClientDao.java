package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {

	private final ClientDao instance = null;

	public ClientDao() {}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(lastname, firstname, email, birthday) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String GET_CLIENTS_COUNT_QUERY = "SELECT COUNT(*) AS count FROM Client;";
	private static final String FIND_CLIENT_QUERY = "SELECT lastname, firstname, email, birthday FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, lastname, firstname, email, birthday FROM Client;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET lastname=?, firstname=?, email=?, birthday=? WHERE id=?;";


	// Insert a client in the database
	public void save(Client client) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(CREATE_CLIENT_QUERY);

			statement.setString(1, client.getLastname().toUpperCase());
			statement.setString(2, client.getFirstname());
			statement.setString(3, client.getEmail());
			statement.setDate(4, Date.valueOf(client.getBirthdate()));

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Problem when inserting the client: " + e.getMessage(), e);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}

	// delete a client by its id
	public void delete(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(DELETE_CLIENT_QUERY);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Problem when deleting the client: " + e.getMessage(), e);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}

	// find a client by its id
	public Client findById(int id) throws DaoException {
		Client client = null;

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(FIND_CLIENT_QUERY);
			statement.setLong(1, id);
			resultSet = statement
					.executeQuery();
			if (resultSet.next()) {
				client = new Client(
						resultSet.getString("lastname"),
						resultSet.getString("firstname"),
						resultSet.getString("email"),
						resultSet.getDate("birthday").toLocalDate()
				);
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when finding the client: " + e.getMessage(), e);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		return client;
	}

	// find all clients
	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery(FIND_CLIENTS_QUERY);
			while (resultSet.next()) {
				clients.add(new Client(
						resultSet.getInt("id"),
						resultSet.getString("lastname"),
						resultSet.getString("firstname"),
						resultSet.getString("email"),
						resultSet.getDate("birthday").toLocalDate()));
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when finding the clients: " + e.getMessage(), e);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		return clients;
	}

	// update a client
	public void update(Client client) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(UPDATE_CLIENT_QUERY);

			statement.setString(1, client.getLastname().toUpperCase());
			statement.setString(2, client.getFirstname());
			statement.setString(3, client.getEmail());
			statement.setDate(4, Date.valueOf(client.getBirthdate()));
			statement.setInt(5, client.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Problem when updating the client: " + e.getMessage(), e);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}

	// count the number of clients
	public int getCount() throws DaoException {
		int count = 0;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery(GET_CLIENTS_COUNT_QUERY);
			if (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when counting the clients: " + e.getMessage(), e);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}

		return count;
	}

	// close the connection
	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// silent
			}
		}
	}

	// close the statement
	private void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// silent
			}
		}
	}

	// close the result set
	private void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// silent
			}
		}
	}
}