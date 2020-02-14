package com.goinside.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.goinside.jpa2.dao.VendedorDAO;
import com.goinside.jpa2.model.Vendedor;
import com.goinside.jpa2.util.jpa.Transactional;

public class CadastroVendedorService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private VendedorDAO vendedorDAO;

	@Transactional
	public void salvar(Vendedor vendedor) throws NegocioException {

		try {
			this.vendedorDAO.salvar(vendedor);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("O CPF " + vendedor.getCpf() + " já foi registrado!");
			} else {
				throw new NegocioException(e.getMessage());
			}
		}

	}
}
