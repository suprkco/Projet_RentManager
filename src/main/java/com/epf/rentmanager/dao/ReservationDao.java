package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

import com.epf.rentmanager.persistence.ConnectionManager;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

@Repository
public class ReservationDao {

	private final ReservationDao instance = null;

	@Autowired
	public ReservationDao() {}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, startTime, endTime) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, startTime, endTime FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, startTime, endTime FROM Reservation WHERE vehicle_id=?;";
	private static final String GET_RESERVATIONS_COUNT_QUERY = "SELECT COUNT(*) AS count FROM Reservation;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, startTime, endTime FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, startTime, endTime FROM Reservation;";

	// Insert a reservation in the database
	public void save(Reservation reservation) throws DaoException {
	}

	// delete a reservation by its id
	public void delete(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch	(SQLException e) {
			throw new DaoException("Error while deleting a reservation" + e.getMessage(), e);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}

	// find a reservation by its id
	public Reservation findById(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Reservation reservation = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(FIND_RESERVATION_QUERY);
			statement.setInt(1, id);
			resultSet = statement
					.executeQuery();
			if (resultSet.next()) {
				reservation = new Reservation(
						resultSet.getInt("id"),
						resultSet.getInt("client_id"),
						resultSet.getInt("vehicle_id"),
						resultSet.getDate("startTime").toLocalDate(),
						resultSet.getDate("endTime").toLocalDate()
				);
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when finding the reservation: " + e.getMessage(), e);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		return reservation;
	}

	// update a reservation
	public void update(Reservation reservation) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		// TODO
	}

	// find all the reservations
	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery(FIND_RESERVATIONS_QUERY);
			while (resultSet.next()) {
				reservations.add(new Reservation(
						resultSet.getInt("id"),
						resultSet.getInt("client_id"),
						resultSet.getInt("vehicle_id"),
						resultSet.getDate("startTime").toLocalDate(),
						resultSet.getDate("endTime").toLocalDate()));
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when retrieving the reservations: " + e.getMessage(), e);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}

		return reservations;
	}


	// find a reservation by its client id
	public List<Reservation> findResaByClient(int clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY)) {
			statement.setLong(1, clientId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Reservation reservation = new Reservation(
							resultSet.getInt("id"),
							clientId,
							resultSet.getInt("vehicle_id"),
							resultSet.getDate("startTime").toLocalDate(),
							resultSet.getDate("endTime").toLocalDate()
					);
					reservations.add(reservation);
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when retrieving the reservations by client id: " + e.getMessage(), e);
		}
		return reservations;
	}

	// find a reservation by its vehicle id
	public List<Reservation> findResaByVehicle(int vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY)) {
			statement.setLong(1, vehicleId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Reservation reservation = new Reservation(
							resultSet.getInt("id"),
							resultSet.getInt("client_id"),
							vehicleId,
							resultSet.getDate("startTime").toLocalDate(),
							resultSet.getDate("endTime").toLocalDate()
					);
					reservations.add(reservation);
				}
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when retrieving the reservations by vehicle id: " + e.getMessage(), e);
		}
		return reservations;
	}

	// count the number of reservations
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
					.executeQuery(GET_RESERVATIONS_COUNT_QUERY);
			if (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when retrieving the reservations count: " + e.getMessage(), e);
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
