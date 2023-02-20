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
	private static final String FIND_VEHICLE_QUERY = "SELECT id, manufacturer, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, manufacturer, nb_places FROM Vehicle;";
	
	public long create(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public long delete(Vehicle vehicle) throws DaoException {
		return 0;
	}

	public Vehicle findById(long id) throws DaoException {
		return new Vehicle();
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_VEHICLES_QUERY);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String manufacturer = resultSet.getString("manufacturer");
				int nb_places = resultSet.getInt("nb_places");

				vehicles.add(new Vehicle(id, manufacturer, nb_places));
			}
		} catch (SQLException e) {
			throw new DaoException();
		}

		return vehicles;
		
	}
	

}
