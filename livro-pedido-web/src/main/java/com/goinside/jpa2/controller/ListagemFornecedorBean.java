package com.goinside.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.goinside.jpa2.dao.FornecedorDAO;
import com.goinside.jpa2.model.Fornecedor;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ListagemFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	FornecedorDAO fornecedorDAO;

	private List<Fornecedor> fornecedores = new ArrayList<>();

	private Fornecedor fornecedorSelecionado;

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void excluir(){
		
			try {
				fornecedorDAO.excluir(fornecedorSelecionado);
				this.fornecedores.remove(fornecedorSelecionado);
				FacesUtil.addSuccessMessage("Fornecedor " + fornecedorSelecionado.getNomeFantasia() + " excluído com sucesso.");
			} catch (NegocioException e) {
				FacesUtil.addErrorMessage(e.getMessage());
			}
			
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		fornecedores = fornecedorDAO.buscarTodos();
	}
}
