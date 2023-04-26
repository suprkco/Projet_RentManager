package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.model.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.VehicleDao;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.exception.DaoException;

@Service
public class VehicleService {

	private final VehicleDao vehicleDao;

	public VehicleService(VehicleDao vehicleDao) { this.vehicleDao = vehicleDao; }

	public void create(Vehicle vehicle) throws ServiceException, DaoException {
		try {
			vehicleDao.save(vehicle);
		} catch (Exception e) {
			throw new ServiceException("Problem when creating the vehicle " + e.getMessage(), e);
		}
	}

	public Vehicle findById(int id) throws ServiceException {
		try {
			return vehicleDao.findById(id);
		} catch (Exception e) {
			throw new ServiceException("Problem when finding the vehicle " + e.getMessage(), e);
		}
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return vehicleDao.findAll();
		} catch (Exception e) {
			throw new ServiceException("Problem when retrieving the vehicles " + e.getMessage(), e);
		}
	}

	public void addVehicle(String manufacturer, String model, int nb_places) throws ServiceException, DaoException {
		Vehicle vehicle = new Vehicle(manufacturer, model, nb_places);
		try {
			vehicleDao.save(vehicle);
		} catch (Exception e) {
			throw new ServiceException("Problem when creating the vehicle " + e.getMessage(), e);
		}
	}

	public void update(int id, String manufacturer, String model, int nb_places) throws ServiceException, DaoException {
		Vehicle vehicle = new Vehicle(id, manufacturer, model, nb_places);
		// mise à jour du vehicle
		try {
			vehicleDao.update(vehicle);
		} catch (DaoException e) {
			throw new ServiceException("Problem when updating the vehicle " + e.getMessage(), e);
		}
	}

	public int getCount() throws ServiceException {
		try {
			return vehicleDao.getCount();
		} catch (DaoException e) {
			throw new ServiceException("Problem when counting the vehicles " + e.getMessage(), e);
		}
	}

	public void delete(int id) throws ServiceException, DaoException {
		// suppression du vehicle
		try {
			vehicleDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Problem when deleting the vehicle " + e.getMessage(), e);
		}
	}
}
