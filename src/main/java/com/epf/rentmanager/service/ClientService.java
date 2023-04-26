package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.util.List;

import com.epf.rentmanager.model.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;

@Service
public class ClientService {

	private final ClientDao clientDao;

	private ClientService(ClientDao clientDao){ this.clientDao = clientDao; }

	// We will prevent the creation or update of a Client if its name is empty. If such an operation is attempted, a ServiceException will be thrown.
	public void create(Client client) throws ServiceException {
		try {
			clientDao.save(client);
		} catch (Exception e) {
			throw new ServiceException("Problem when creating the client", e);
		}
	}

	public Client findById(int id) throws ServiceException {
		try {
			return clientDao.findById(id);
		} catch (Exception e) {
			throw new ServiceException("Problem when finding the client", e);
		}
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		} catch (Exception e) {
			throw new ServiceException("Problem when retrieving the clients" + e.getMessage(), e);
		}
	}



	// create a client
	public void addClient(String lastName, String firstName, String email, LocalDate birthDate) throws ServiceException {
		Client newClient = new Client(lastName, firstName, email, birthDate);
		// appel à la méthode de persistence pour enregistrer l'utilisateur dans la base de données
		try {
			clientDao.save(newClient);
		} catch (DaoException e) {
			throw new ServiceException("Problem when creating the client", e);
		}
	}

	// modify a client
	public void update(int id, String lastname, String firstname, String email, LocalDate birthdate) throws ServiceException {
		Client client = new Client(id, lastname, firstname, email, birthdate);
		// mise à jour du client
		try {
			clientDao.update(client);
		} catch (DaoException e) {
			throw new ServiceException("Problem when updating the client " + e.getMessage(), e);

		}
	}

	// get number of clients
	public int getCount() throws ServiceException {
		try {
			return clientDao.getCount();
		} catch (DaoException e) {
			throw new ServiceException("Problem when counting the clients " + e.getMessage(), e);
		}
	}

	// delete a client
	public void delete(int id) throws ServiceException {
		// suppression du client
		try {
			clientDao.delete(id);
		} catch (Exception e) {
			throw new ServiceException("Problem when deleting the client " + e.getMessage(), e);
		}
	}
}