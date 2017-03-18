package com.komet.ws.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.komet.ws.rest.dao.ClienteDAO;
import com.komet.ws.rest.entities.Cliente;
import com.komet.ws.rest.vo.ClienteVO;
import com.komet.ws.rest.vo.MensajeVO;


@Path("/clienteService")
@Stateless
public class ClienteServiceImpl   {
	
	@Resource
	private SessionContext contexto;
	
	@EJB
	private ClienteDAO clienteDAO;
	
	@GET
	@Path("/listarClientes")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ClienteVO> listarClientes() {
		List<Cliente> clienteList;
		List<ClienteVO> clienteVOList;

		try {
			clienteList = clienteDAO.findAll();
			if (clienteList != null) {

				clienteVOList = new ArrayList<ClienteVO>();
				for (Cliente item : clienteList) {
					clienteVOList.add(entityToVO(item));
				}

				return clienteVOList;
			} else
				return clienteVOList = null;
		} catch (Throwable t) {
			contexto.setRollbackOnly();
			return null;
		}
	}

	@POST
	@Path("/insertCliente")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public MensajeVO insertCliente(ClienteVO clienteVO) {
		try {
			Cliente cliente = new Cliente();
			cliente = voToEntity(clienteVO);
			
			if(cliente != null){
				clienteDAO.insertCliente(cliente);
				
				MensajeVO mensaje = new MensajeVO();
				mensaje.setTipo("Success");
				mensaje.setMensaje("Se guardó el cliente con exito!!");
				return mensaje;
			}else{
				MensajeVO mensaje = new MensajeVO();
				mensaje.setTipo("Error");
				mensaje.setMensaje("No se guardó el cliente!!");
				return mensaje;
			}

		} catch (Throwable t) {
			//contexto.setRollbackOnly();
			MensajeVO mensaje = new MensajeVO();
			mensaje.setTipo("Error");
			mensaje.setMensaje("Error en la transacción!!");
			return mensaje;
		}
	}

	@POST
	@Path("/updateCliente")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public MensajeVO updateCliente(ClienteVO clienteVO) {
		try {
			Cliente cliente = new Cliente();
			cliente = voToEntity(clienteVO);
			
			if(cliente != null){
				cliente.setIdCliente(clienteVO.getIdCliente());
				clienteDAO.updateCliente(cliente);
				
				MensajeVO mensaje = new MensajeVO();
				mensaje.setTipo("Success");
				mensaje.setMensaje("Se modificó el cliente con exito!!");
				return mensaje;
			}else{
				MensajeVO mensaje = new MensajeVO();
				mensaje.setTipo("Error");
				mensaje.setMensaje("No se modificó el cliente!!");
				return mensaje;
			}

		} catch (Throwable t) {
			//contexto.setRollbackOnly();
			MensajeVO mensaje = new MensajeVO();
			mensaje.setTipo("Error");
			mensaje.setMensaje("Error en la transacción!!");
			return mensaje;
		}
	}

	@POST
	@Path("/deleteCliente")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public MensajeVO deleteCliente(ClienteVO clienteVO) {
		try {
			Cliente cliente = new Cliente();
			cliente = voToEntity(clienteVO);
			
			if(cliente != null){
				cliente.setIdCliente(clienteVO.getIdCliente());
				clienteDAO.deleteCliente(cliente);
				
				MensajeVO mensaje = new MensajeVO();
				mensaje.setTipo("Success");
				mensaje.setMensaje("Se eliminó el cliente con exito!!");
				return mensaje;
			}else{
				MensajeVO mensaje = new MensajeVO();
				mensaje.setTipo("Error");
				mensaje.setMensaje("No se eliminó el cliente!!");
				return mensaje;
			}

		} catch (Throwable t) {
			//contexto.setRollbackOnly();
			MensajeVO mensaje = new MensajeVO();
			mensaje.setTipo("Error");
			mensaje.setMensaje("Error en la transacción!!");
			return mensaje;
		}
	}
	
	private ClienteVO entityToVO(Cliente cliente) {
		if(cliente != null){
			ClienteVO clienteVO = new ClienteVO();
	
			clienteVO.setIdCliente(cliente.getIdCliente());
			clienteVO.setNombres(cliente.getNombres());
			clienteVO.setApellidos(cliente.getApellidos());
			clienteVO.setIdentificacion(cliente.getIdentificacion());
			clienteVO.setDireccion(cliente.getDireccion());
			clienteVO.setTelefono(cliente.getTelefono());
			clienteVO.setEmail(cliente.getEmail());
	
			return clienteVO;
		}else
			return new ClienteVO();
	}
	
	private Cliente voToEntity(ClienteVO clienteVO) {
		if(clienteVO != null){
			Cliente cliente = new Cliente();
	
			cliente.setNombres(clienteVO.getNombres());
			cliente.setApellidos(clienteVO.getApellidos());
			cliente.setIdentificacion(clienteVO.getIdentificacion());
			cliente.setDireccion(clienteVO.getDireccion());
			cliente.setTelefono(clienteVO.getTelefono());
			cliente.setEmail(clienteVO.getEmail());
	
			return cliente;
		}else
			return new Cliente();
	}

}
