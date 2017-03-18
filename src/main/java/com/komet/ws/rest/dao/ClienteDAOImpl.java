package com.komet.ws.rest.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.komet.ws.rest.entities.Cliente;

@Stateless
public class ClienteDAOImpl implements ClienteDAO {
	
	@PersistenceContext(unitName = "KometRestPU")
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() {
		return em.createNamedQuery("Cliente.findAll").getResultList();
	}

	public void insertCliente(Cliente cliente) {
		em.persist(cliente);
	}

	public void updateCliente(Cliente cliente) {
		em.merge(cliente);
	}

	public void deleteCliente(Cliente cliente) {
		cliente = em.find(Cliente.class, cliente.getIdCliente());
		em.remove(cliente);
	}

}
