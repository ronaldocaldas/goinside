package com.goinside.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.goinside.jpa2.model.Livro;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jpa.Transactional;

public class LivroDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7498888190265765831L;
	@Inject
	private EntityManager manager;

	public Livro buscarPeloId(Long id) {
		return manager.find(Livro.class, id);
	}

	public void salvar(Livro livro) {
		manager.merge(livro);
	}

	public List<Livro> buscarTodos() {
		return manager.createQuery("from Livro", Livro.class).getResultList();
	}

	@Transactional
	public void excluir(Livro livro) throws NegocioException {
		livro = buscarPeloId(livro.getId());
		try {
			manager.remove(livro);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Livro não pode ser excluído.");
		}
	}

	public Livro buscarComFornecedorPeloCodigo(Long id) {
		return manager.createQuery("select c from Livro c inner join fetch c.fabricante where c.id = :id", Livro.class)
				.setParameter("id", id).getSingleResult();
	}

	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}
}
