package com.epf.rentmanager.exception;

import java.sql.SQLException;

public class DaoException extends Exception {

    public DaoException(String message, SQLException e) {
        super(message);
    }
}
