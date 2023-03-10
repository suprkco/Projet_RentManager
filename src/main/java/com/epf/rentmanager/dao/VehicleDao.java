package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	public static VehicleDao getInstance() {
		if(instance == null) {
			instance = new VehicleDao();
		}
		return instance;
	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(manufacturer, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String GET_VEHICLES_COUNT_QUERY = "SELECT COUNT(*) AS count FROM Vehicle;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, manufacturer, model, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, manufacturer, model, nb_places FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		return 0;
	}

	// find a vehicle by its id
	public Vehicle findById(int id) throws DaoException {
		Vehicle vehicle = new Vehicle();
		try {
			// get connection
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			// if there is a result, create a vehicle
			if (resultSet.next()) {
				String manufacturer = resultSet.getString("manufacturer");
				String model = resultSet.getString("model");
				int nb_places = resultSet.getInt("nb_places");
				// set the vehicle's attributes
				vehicle.setId(id);
				vehicle.setManufacturer(manufacturer);
				vehicle.setModel(model);
				vehicle.setNb_places(nb_places);
			}
		// if there is no result, throw an exception
		} catch (SQLException e) {
			throw new DaoException("Problem during the recovery of the vehicle: " + e.getMessage());
		}
		// return the vehicle
		return vehicle;
	}

	// find all vehicles
	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			// get connection
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_VEHICLES_QUERY);
			// for each result, create a vehicle
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String manufacturer = resultSet.getString("manufacturer");
				String model = resultSet.getString("model");
				int nb_places = resultSet.getInt("nb_places");
				// add the vehicle to the list
				vehicles.add(new Vehicle(id, manufacturer, model, nb_places));
			}
		// if there is no result, throw an exception
		} catch (SQLException e) {
			throw new DaoException("Problem during the recovery of the vehicles: " + e.getMessage());
		}
		// return the list of vehicles
		return vehicles;
	}

	// get the number of vehicles
	public int getCount() throws DaoException {
		int count = 0;
		try {
			// get connection
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(GET_VEHICLES_COUNT_QUERY);
			// if there is a result, get the count
			if (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		// if there is no result, throw an exception
		} catch (SQLException e) {
			throw new DaoException("Problem during the recovery of the vehicles count: " + e.getMessage());
		}
		// return the count
		return count;
	}

    // create a vehicle

	// delete a vehicle

}
