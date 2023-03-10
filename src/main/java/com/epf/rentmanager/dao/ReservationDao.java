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

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	public static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, startTime, endTime) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, startTime, endTime FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, startTime, endTime FROM Reservation WHERE vehicle_id=?;";
	private static final String GET_RESERVATIONS_COUNT_QUERY = "SELECT COUNT(*) AS count FROM Reservation;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, startTime, endTime FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, startTime, endTime FROM Reservation;";
		
	public long create(Reservation reservation) throws DaoException {
		return 0;
	}
	
	public long delete(Reservation reservation) throws DaoException {
		return 0;
	}

	// find a reservation by its id
	public Reservation findById(int id) throws DaoException {
		Reservation reservation = new Reservation();
		try {
			// get connection
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_RESERVATION_QUERY);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			// if the reservation exists
			if (resultSet.next()) {
				int client_id = resultSet.getInt("client_id");
				int vehicle_id = resultSet.getInt("vehicle_id");
				LocalDate startTime = resultSet.getDate("startTime").toLocalDate();
				LocalDate endTime = resultSet.getDate("endTime").toLocalDate();
				// set the reservation's attributes
				reservation.setId(id);
				reservation.setClient_id(client_id);
				reservation.setVehicle_id(vehicle_id);
				reservation.setStartTime(String.valueOf(startTime));
				reservation.setEndTime(String.valueOf(endTime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("Problem when retrieving the reservation " + e.getMessage());
		}

		return reservation;
	}

	// find a reservation by its client id
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		return new ArrayList<Reservation>();
	}

	// find a reservation by its vehicle id
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		return new ArrayList<Reservation>();
	}

	// find all the reservations
	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try {
			// get connection
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_RESERVATIONS_QUERY);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int client_id = resultSet.getInt("client_id");
				int vehicle_id = resultSet.getInt("vehicle_id");
				LocalDate startTime = resultSet.getDate("startTime").toLocalDate();
				LocalDate endTime = resultSet.getDate("endTime").toLocalDate();

				reservations.add(new Reservation(id, client_id, vehicle_id, startTime, endTime));
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when retrieving the reservations: " + e.getMessage());
		}
		return reservations;
	}

	public List<Reservation> findAllDetails() throws DaoException {
		List<Reservation> reservations = new ArrayList<>();
		try {
			// get connection
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_RESERVATIONS_QUERY);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int client_id = resultSet.getInt("client_id");
				int vehicle_id = resultSet.getInt("vehicle_id");
				LocalDate startTime = resultSet.getDate("startTime").toLocalDate();
				LocalDate endTime = resultSet.getDate("endTime").toLocalDate();

				String client_info = ClientDao.getInstance().findById(client_id).getClient_info();
				String vehicle_info = VehicleDao.getInstance().findById(vehicle_id).getVehicle_info();

				reservations.add(new Reservation(id, client_id, vehicle_id, startTime, endTime, client_info, vehicle_info));
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when retrieving the reservations: " + e.getMessage());
		}

		return reservations;
	}

	// count the number of reservations
    public int getCount() throws DaoException {
		int count = 0;
		try {
			// get connection
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(GET_RESERVATIONS_COUNT_QUERY);
			if (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		// if there is no reservation, throw an exception
		} catch (SQLException e) {
			throw new DaoException("Problem when retrieving the reservations count: " + e.getMessage());
		}
		// return the number of reservations
		return count;
    }
}
