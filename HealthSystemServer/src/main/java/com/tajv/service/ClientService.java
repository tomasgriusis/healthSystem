package com.tajv.service;

import com.tajv.model.Client;

public interface ClientService {

	public void addClient(Client client);

	public void updateClient(Client client);

	public Client getClientByEmail(String email);

	public Client getClientByPhone(String phone);

	public Client getClientById(int id);
}
