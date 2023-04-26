package com.epf.rentmanager.servlet.user;

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

@WebServlet("/users/details")
public class DetailUserServlet extends HttpServlet {
    /**
     * Display details of a user
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter("id"));

        // On définit la liste des vehicules proprietaire a null par defaut
        request.setAttribute("vehicles", null);

        // recupere le client concerné
        try {
            request.setAttribute("client", clientService.findById(id));
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
        int lenReservations = parseInt(String.valueOf(reservationClient.size()));
        request.setAttribute("lenReservations", lenReservations);
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
