package com.goinside.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;

import com.goinside.jpa2.model.Fornecedor;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jpa.Transactional;


public class FornecedorDAO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;

	public void salvar(Fornecedor fornecedor) {
		em.merge(fornecedor);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> buscarTodos() {
		return em.createQuery("from Fornecedor").getResultList();
	}
	
	@Transactional
	public void excluir(Fornecedor fornecedorSelecionado) throws NegocioException {
		try {
			Fornecedor fornecedor = em.find(Fornecedor.class, fornecedorSelecionado.getCodigo());
			em.remove(fornecedor);
			em.flush();
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException("O fornecedor "+fornecedorSelecionado.getNomeFantasia()+" não pode ser excluído!");
			}else{
				throw new NegocioException(e.getMessage());
			}
		}
		
	}

	public Fornecedor buscarPeloCodigo(Long codigo) {
		return em.find(Fornecedor.class, codigo);
	}
	
	public void setEntityManager(EntityManager manager) {
		this.em = manager;
	}

}
