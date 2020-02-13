package com.goinside.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.goinside.jpa2.dao.FornecedorDAO;
import com.goinside.jpa2.model.Fornecedor;
import com.goinside.jpa2.util.jpa.Transactional;

public class CadastroFornecedorService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FornecedorDAO fornecedorDAO;

	@Transactional
	public void salvar(Fornecedor fornecedor) throws NegocioException {

		try {
			this.fornecedorDAO.salvar(fornecedor);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("O código " + fornecedor.getCodigo() + " já foi registrado!");

			} else {
				throw new NegocioException(e.getMessage());
			}
		}

	}
}
