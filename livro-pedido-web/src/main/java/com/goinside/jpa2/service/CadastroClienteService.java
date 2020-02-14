package com.goinside.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.goinside.jpa2.dao.ClienteDAO;
import com.goinside.jpa2.model.Cliente;
import com.goinside.jpa2.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteDAO clienteDAO;

	@Transactional
	public void salvar(Cliente cliente) throws NegocioException {

		try {
			this.clienteDAO.salvar(cliente);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("O CPF " + cliente.getCpf() + " já foi registrado!");
			} else {
				throw new NegocioException(e.getMessage());
			}
		}

	}
}
