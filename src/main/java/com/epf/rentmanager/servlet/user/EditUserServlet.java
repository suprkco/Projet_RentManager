package com.epf.rentmanager.servlet.user;

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

@WebServlet("/users/edit")
public class EditUserServlet extends HttpServlet {
    /**
     * Edit a user
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    // recupere les infos de l'utilisateur specifié et les affiche dans le formulaire
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            request.setAttribute("client", clientService.findById(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
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
            clientService.update(id, lastName, firstName, email, birthdate);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect(request.getContextPath() + "/users");
    }

}
