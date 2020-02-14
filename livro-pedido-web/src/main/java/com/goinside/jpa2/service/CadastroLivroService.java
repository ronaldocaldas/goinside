package com.goinside.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.goinside.jpa2.dao.LivroDAO;
import com.goinside.jpa2.model.Livro;
import com.goinside.jpa2.util.jpa.Transactional;

public class CadastroLivroService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private LivroDAO livroDAO;

	@Transactional
	public void salvar(Livro livro) throws NegocioException {

		try {
			this.livroDAO.salvar(livro);
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("O ISBN " + livro.getIsbn() + " já foi registrado!");

			} else {
				throw new NegocioException(e.getMessage());
			}
		}

	}
}
