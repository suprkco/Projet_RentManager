package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	/**
	 * Display the home page
	 */
	private static final long serialVersionUID = 1L;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("nbClients", clientService.getCount());
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		try {
			request.setAttribute("nbVehicles", vehicleService.getCount());
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}
		try {
			request.setAttribute("nbReservations", reservationService.getCount());
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}
