package com.epf.rentmanager.servlet.reservation;

import com.epf.rentmanager.exception.DaoException;
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

@WebServlet("/rents/create")
public class CreateReservationServlet extends HttpServlet {
    /**
     * Create a new reservation
     */

    private static final long serialVersionUID = 1L;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // envoi la liste complète de CLient et de Vehicle to the view
        try {
            request.setAttribute("clients", clientService.findAll());
            request.setAttribute("vehicles", vehicleService.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // récupérer les paramètres du formulaire
        String clientIdStr = request.getParameter("client");
        String vehicleIdStr = request.getParameter("vehicle");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");

        // valider les paramètres
        if (clientIdStr == null || clientIdStr.isEmpty() ||
                vehicleIdStr == null || vehicleIdStr.isEmpty() ||
                startTimeStr == null || startTimeStr.isEmpty() ||
                endTimeStr == null || endTimeStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid parameters");
            return;
        }
        int clientId, vehicleId;
        try {
            clientId = Integer.parseInt(clientIdStr);
            vehicleId = Integer.parseInt(vehicleIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
            return;
        }
        LocalDate startTime, endTime;
        try {
            startTime = LocalDate.parse(startTimeStr);
            endTime = LocalDate.parse(endTimeStr);
        } catch (DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
            return;
        }

        // créer la réservation
        try {
            reservationService.create(clientId, vehicleId, startTime, endTime);
        } catch (ServiceException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/rents");

    }
}
