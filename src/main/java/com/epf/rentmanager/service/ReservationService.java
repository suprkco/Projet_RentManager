package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.util.List;

import com.epf.rentmanager.model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ReservationDao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;

    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public void create(int clientId, int vehicleId, LocalDate startTime, LocalDate endTime) throws ServiceException {
        Reservation reservation = new Reservation(clientId, vehicleId, startTime, endTime);
        try {
            reservationDao.save(reservation);
        } catch (Exception e) {
            throw new ServiceException("Problem when creating the reservation " + e.getMessage(), e);
        }
    }

    public Reservation findById(int id) throws ServiceException {
        try {
            return reservationDao.findById(id);
        } catch (Exception e) {
            throw new ServiceException("Problem when finding the reservation " + e.getMessage(), e);
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return reservationDao.findAll();
        } catch (Exception e) {
            throw new ServiceException("Problem when retrieving the reservations " + e.getMessage(), e);
        }
    }

    public List<Reservation> findResaByClient(int id) throws ServiceException {
        try {
            return reservationDao.findResaByClient(id);
        } catch (Exception e) {
            throw new ServiceException("Problem when retrieving the reservations " + e.getMessage(), e);
        }
    }

    public List<Reservation> findResaByVehicle(int id) throws ServiceException {
        try {
            return reservationDao.findResaByVehicle(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problem when retrieving the reservations " + e.getMessage(), e);
        }
    }

    // modify a reservation
    public void update(int id, int client_id, int vehicle_id, LocalDate startTime, LocalDate endTime) throws ServiceException {
        Reservation reservation = new Reservation(id, client_id, vehicle_id, startTime, endTime);
        // mise a jour de la reservation
        try {
            reservationDao.update(reservation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Problem when updating the reservation " + e.getMessage(), e);
        }
    }

    // get number of reservations
    public int getCount() throws ServiceException {
        try {
            return reservationDao.getCount();
        } catch (DaoException e) {
            throw new ServiceException("Problem when counting the reservations " + e.getMessage(), e);
        }
    }

    // delete a reservation
    public void delete(int id) throws ServiceException {
        try {
            reservationDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Problem when deleting the reservation " + e.getMessage(), e);
        }
    }
}
