package com.komet.ws.rest.dao;

import java.util.List;

import com.komet.ws.rest.entities.Cliente;

public interface ClienteDAO {
	
	public List<Cliente> findAll();

	public void insertCliente(Cliente cliente);

	public void updateCliente(Cliente cliente);

	public void deleteCliente(Cliente cliente);

}
