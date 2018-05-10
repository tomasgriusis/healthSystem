package com.tajv.dao;

import com.tajv.model.Client;

public interface ClientDao {

	public Client getClientById(int id);

	public Client getClientByEmail(String email);

	public Client getClientByPhone(String phone);

	public void saveOrUpdateClient(Client client);

}
