package com.epf.rentmanager.servlet.vehicle;

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

@WebServlet("/vehicles/create")
public class CreateVehicleServlet extends HttpServlet {
    /**
     * Create a new vehicle
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // récupére les paramètres du formulaire
        String manufacturer = request.getParameter("manufacturer");
        String model = request.getParameter("model");
        int nb_places = Integer.parseInt(request.getParameter("nb_places"));

        // valider les paramètres
        if (manufacturer == null || manufacturer.isEmpty() ||
                model == null || model.isEmpty() ||
                nb_places == 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid parameters");
            return;
        }

        if (nb_places < 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number of seats");
            return;
        }

        try {
            vehicleService.addVehicle(manufacturer, model, nb_places);
        } catch (ServiceException | DaoException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/cars");

    }
}
