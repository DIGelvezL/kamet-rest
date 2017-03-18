package com.komet.ws.rest.service;

import java.util.List;

import javax.ejb.Local;

import com.komet.ws.rest.vo.ClienteVO;


@Local
public interface ClienteService {
	
	public List<ClienteVO> listarClientes();

	public String insertCliente(ClienteVO clienteVO);

	public void updateCliente(ClienteVO clienteVO);

	public void deleteCliente(ClienteVO clienteVO);
}
