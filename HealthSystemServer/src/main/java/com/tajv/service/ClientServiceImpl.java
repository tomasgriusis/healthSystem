package com.tajv.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tajv.dao.ClientDao;
import com.tajv.model.Client;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Transactional
	public void addClient(Client client) {
		this.clientDao.saveOrUpdateClient(client);
	}

	@Transactional
	public void updateClient(Client client) {
		this.clientDao.saveOrUpdateClient(client);
	}

	@Transactional
	public Client getClientByEmail(String email) {
		return this.clientDao.getClientByEmail(email);
	}

	@Transactional
	public Client getClientByPhone(String phone) {
		return this.clientDao.getClientByPhone(phone);
	}

	@Transactional
	public Client getClientById(int id) {
		return this.clientDao.getClientById(id);
	}
}
