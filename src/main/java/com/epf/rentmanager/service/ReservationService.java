package com.epf.rentmanager.service;

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

    public void create(Reservation reservation) throws ServiceException {
        try {
            reservationDao.save(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problem when creating the reservation " + e.getMessage(), e);
        }
    }

    public Reservation findById(int id) throws ServiceException {
        try {
            return reservationDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problem when finding the reservation " + e.getMessage(), e);
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return reservationDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problem when retrieving the reservations " + e.getMessage(), e);
        }
    }

    public List<Reservation> findResaByClient(int id) throws ServiceException {
        try {
            return reservationDao.findResaByClient(id);
        } catch (DaoException e) {
            e.printStackTrace();
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
    public void update(Reservation reservation) throws ServiceException {
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
