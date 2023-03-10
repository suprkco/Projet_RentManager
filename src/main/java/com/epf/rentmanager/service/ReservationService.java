package com.epf.rentmanager.service;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.dao.ReservationDao;

import java.util.List;

public class ReservationService {

    private ReservationDao reservationDao;

    public static ReservationService instance;

    private ReservationService() { this.reservationDao = ReservationDao.getInstance(); }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }

    public long create(Reservation reservation) throws ServiceException {
        return 0;
    }

    public Reservation findById(long id) throws ServiceException {
        return new Reservation();
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return ReservationDao.getInstance().findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problem when retrieving the reservations " + e.getMessage());
        }
    }

    public List<Reservation> findAllDetails() throws ServiceException {
        try {
            return ReservationDao.getInstance().findAllDetails();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("Problem when retrieving the reservations " + e.getMessage());
        }
    }

    // get number of reservations
    public int getCount() {
        try {
            return ReservationDao.getInstance().getCount();
        } catch (DaoException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
