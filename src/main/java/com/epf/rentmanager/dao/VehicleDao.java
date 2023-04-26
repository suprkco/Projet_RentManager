package com.epf.rentmanager.dao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleDao {

	private final VehicleDao instance = null;

	@Autowired
	public VehicleDao() {}

	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String INSERT_VEHICLE_QUERY = "INSERT INTO Vehicle(manufacturer, model, nb_places) VALUES(?, ?, ?);";
	private static final String GET_VEHICLES_COUNT_QUERY = "SELECT COUNT(*) AS count FROM Vehicle;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, manufacturer, model, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, manufacturer, model, nb_places FROM Vehicle;";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET manufacturer=?, model=?, nb_places=? WHERE id=?;";

	// Insert a vehicle in the database
	// When creating or updating a Client, we will make sure that his last name is recorded in CAPITALS in the database.
	public void save(Vehicle vehicle) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(INSERT_VEHICLE_QUERY);
			statement.setString(1, vehicle.getManufacturer());
			statement.setString(2, vehicle.getModel());
			statement.setInt(3, vehicle.getNb_places());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Problem when inserting the vehicle: " + e.getMessage(), e);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}

	// delete a vehicle by its id
	public void delete(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Problem when deleting the client: " + e.getMessage(), e);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}

	// find a vehicle by its id
	public Vehicle findById(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Vehicle vehicle = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(FIND_VEHICLE_QUERY);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				vehicle = new Vehicle(
						resultSet.getInt("id"),
						resultSet.getString("manufacturer"),
						resultSet.getString("model"),
						resultSet.getInt("nb_places")
				);
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when finding the vehicle: " + e.getMessage(), e);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		return vehicle;
	}

	// find all vehicles
	public List<Vehicle> findAll() throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Vehicle> vehicles = new ArrayList<>();

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(FIND_VEHICLES_QUERY);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				vehicles.add(new Vehicle(
						resultSet.getInt("id"),
						resultSet.getString("manufacturer"),
						resultSet.getString("model"),
						resultSet.getInt("nb_places")));
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when finding the vehicles: " + e.getMessage(), e);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(connection);
		}
		return vehicles;
	}

	// update a vehicle
	public void update(Vehicle vehicle)throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(UPDATE_VEHICLE_QUERY);

			statement.setString(1, vehicle.getManufacturer());
			statement.setString(2, vehicle.getModel());
			statement.setInt(3, vehicle.getNb_places());
			statement.setInt(4, vehicle.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Problem when updating the client: " + e.getMessage(), e);
		} finally {
			closeStatement(statement);
			closeConnection(connection);
		}
	}

	// count the number of vehicles
	public int getCount() throws DaoException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int count = 0;

		try {
			// get connection
			connection = ConnectionManager.getConnection();
			statement = connection.prepareStatement(GET_VEHICLES_COUNT_QUERY);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			throw new DaoException("Problem when counting the vehicles: " + e.getMessage(), e);
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
