package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet("/rents")
public class ReservationsServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ReservationService reservationService = ReservationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get all reservations
        try {
            request.setAttribute("reservations", reservationService.findAllDetails());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
    }
}
