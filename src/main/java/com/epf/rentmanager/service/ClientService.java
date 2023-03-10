package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}

	// We will prevent the creation or update of a Client if its name is empty. If such an operation is attempted, a ServiceException will be thrown.
	public long create(Client client) throws ServiceException {
		// TODO: create a client
		return 0;
	}

	public Client findById(int id) throws ServiceException {
		// TODO: retrieve a client by its id
		return new Client();
	}

	public List<Client> findAll() throws ServiceException {
		// TODO: recover all customers
		try {
			return ClientDao.getInstance().findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("Problem when retrieving the clients" + e.getMessage());
		}
	}

	// get number of clients
	public int getCount() {
		try {
			return ClientDao.getInstance().getCount();
		} catch (DaoException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// create a client
	public void addClient(String lastName, String firstName, String email, LocalDate birthDate) throws SQLException {
		// création d'un nouvel utilisateur
		Client newClient = new Client();
		newClient.setLastname(lastName);
		newClient.setFirstname(firstName);
		newClient.setEmail(email);
		newClient.setBirthdate(birthDate);

		// appel à la méthode de persistence pour enregistrer l'utilisateur dans la base de données
		clientDao.insert(newClient);
	}
}
