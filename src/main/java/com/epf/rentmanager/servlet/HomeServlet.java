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

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientService clientService = ClientService.getInstance();
	private VehicleService vehicleService = VehicleService.getInstance();
	private ReservationService reservationService = ReservationService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("nbClients", clientService.getCount());
		request.setAttribute("nbVehicles", vehicleService.getCount());
		request.setAttribute("nbReservations", reservationService.getCount());

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

}
