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
	private static final String FIND_CLIENT_QUERY = "SELECT lastname, firstname, email, birthday FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, lastname, firstname, email, birthday FROM Client;";
	
	public long create(Client client) throws DaoException {
		return 0;
	}
	
	public long delete(Client client) throws DaoException {
		return 0;
	}

	public Client findById(long id) throws DaoException {
		return new Client();
	}

	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<Client>();

		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_CLIENTS_QUERY);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String lastname = resultSet.getString("lastname");
				String firstname = resultSet.getString("firstname");
				String email = resultSet.getString("email");
				LocalDate birthday = resultSet.getDate("birthday").toLocalDate();

				clients.add(new Client(id, lastname, firstname, email, birthday));
			}
		} catch (SQLException e) {
			throw new DaoException();
		}

		return clients;
	}

}
