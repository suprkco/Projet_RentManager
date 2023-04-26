package com.epf.rentmanager.servlet.user;

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

@WebServlet("/users/create")
public class CreateUserServlet extends HttpServlet {
    /**
     * Create a new user
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // récupérer les paramètres du formulaire
        String lastName = request.getParameter("lastname");
        String firstName = request.getParameter("firstname");
        String email = request.getParameter("email");
        String birthdateStr = request.getParameter("birthdate");

        // valider les paramètres
        if (lastName == null || lastName.isEmpty() ||
                firstName == null || firstName.isEmpty() ||
                email == null || email.isEmpty() ||
                birthdateStr == null || birthdateStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid parameters");
            return;
        }

        LocalDate birthdate;
        try {
            birthdate = LocalDate.parse(birthdateStr);
        } catch (DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid birthdate format");
            return;
        }

        try {
            clientService.addClient(lastName, firstName, email, birthdate);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/users");

    }
}
