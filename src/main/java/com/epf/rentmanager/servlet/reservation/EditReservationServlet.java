package com.epf.rentmanager.servlet.reservation;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet("/rents/edit")
public class EditReservationServlet extends HttpServlet {
    /**
     * Edit a reservation
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            request.setAttribute("reservation", reservationService.findById(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        // récupère les paramètres du formulaire
        String client_idStr = request.getParameter("client");
        String vehicle_idStr = request.getParameter("vehicle");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");

        // valide les paramètres
        if (client_idStr == null || vehicle_idStr == null || startTimeStr == null || endTimeStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            return;
        }
        int client_id, vehicle_id;
        try {
            client_id = Integer.parseInt(client_idStr);
            vehicle_id = Integer.parseInt(vehicle_idStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid client or vehicle id");
            return;
        }
        LocalDate startTime, endTime;
        try {
            startTime = LocalDate.parse(startTimeStr);
            endTime = LocalDate.parse(endTimeStr);
        } catch (DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
            return;
        }
        if (startTime.isAfter(endTime)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Start date must be before end date");
            return;
        }

        // enregistre la reservation
        try {
            reservationService.update(id, client_id, vehicle_id, startTime, endTime);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/rents");
    }


}
