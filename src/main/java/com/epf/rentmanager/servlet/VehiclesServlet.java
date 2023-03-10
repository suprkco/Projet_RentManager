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

@WebServlet("/cars")
public class VehiclesServlet extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private VehicleService vehicleService = VehicleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get all vehicles
        try {
            request.setAttribute("vehicles", vehicleService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request, response);
    }

}
