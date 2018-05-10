package com.tajv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tajv.model.Client;
import com.tajv.service.ClientService;

@RestController
public class ClientController {

	private ClientService clientService;

	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	@Qualifier(value = "clientService")
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> login(@RequestBody Client client) throws JsonProcessingException {

		System.out.println("CONNECTED TO SERVER...");

		Client clientToGet = clientService.getClientByEmail(client.getEmail());

		if (clientToGet == null) {
			return new ResponseEntity<String>("Client doesn't exist!", HttpStatus.NOT_FOUND);
		}

		if (clientToGet.getEmail().equals(client.getEmail())
				&& clientToGet.getPassword().equals(client.getPassword())) {
			System.out.println(clientToGet.getSurname());
			String clientToTransfer = mapper.writeValueAsString(clientToGet);
			return new ResponseEntity<String>(clientToTransfer, HttpStatus.OK);
		}

		return new ResponseEntity<String>("Username and password do not match!", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/addClient", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addUser(@RequestBody Client client) throws JsonProcessingException {

		System.out.println("CONNECTED TO SERVER...");

		clientService.addClient(client);

		return new ResponseEntity<String>("Client created", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateClient", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> updateUser(@RequestBody Client client) throws JsonProcessingException {

		System.out.println("CONNECTED TO SERVER...");

		Client clientToUpdate = clientService.getClientById(client.getId());

		if (clientToUpdate == null) {
			return new ResponseEntity<>("Client doesn't exist!", HttpStatus.NOT_FOUND);
		}

		// checks if new email is not used in other accounts
		// and if there is checks if account with this email is same as current client
		if ((clientService.getClientByEmail(client.getEmail()) != null
				&& clientService.getClientByEmail(client.getEmail()).getId() != clientToUpdate.getId())) {
			return new ResponseEntity<String>("Email already exists!", HttpStatus.CONFLICT);
		}
		clientService.updateClient(client);
		return new ResponseEntity<String>("Client updated", HttpStatus.OK);
	}

}
