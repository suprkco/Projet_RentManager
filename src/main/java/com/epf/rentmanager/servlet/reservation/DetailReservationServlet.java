package com.epf.rentmanager.servlet.reservation;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import static java.lang.Integer.parseInt;

@WebServlet("/rents/details")
public class DetailReservationServlet extends HttpServlet  {
    /**
     * Display details of a rent
     */

    @Autowired
    private ClientService clientService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        int id = parseInt(request.getParameter("id"));
        try {
            request.setAttribute("rent", reservationService.findById(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        // get reservations by client id
        List<Reservation> reservationClient;
        try {
            reservationClient = reservationService.findResaByClient(id);
            request.setAttribute("reservations", reservationService.findResaByClient(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        // get reservations by vehicle id
        List<Reservation> reservationVehicle;
        try {
            reservationVehicle = reservationService.findResaByVehicle(id);
            request.setAttribute("reservations", reservationService.findResaByVehicle(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/WEB-INF/views/rents/details.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
