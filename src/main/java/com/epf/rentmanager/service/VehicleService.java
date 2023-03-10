package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}

	// We will prevent the creation or update of a Vehicle if its constructor (manufacturer) is empty. We will also make sure that the number of places is greater than 1. If such operations are attempted, we will send a ServiceException.
	public long create(Vehicle vehicle) throws ServiceException {
		// TODO: create a vehicle
		return 0;
	}

	public Vehicle findById(long id) throws ServiceException {
		// TODO: recover a vehicle by its id
		return new Vehicle();
	}

	public List<Vehicle> findAll() throws ServiceException {
		// TODO: recover all customers
		try {
			return VehicleDao.getInstance().findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Problem when retrieving the vehicles " + e.getMessage());
		}
	}

	// get number of vehicles
	public int getCount() {
		try {
			return VehicleDao.getInstance().getCount();
		} catch (DaoException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
