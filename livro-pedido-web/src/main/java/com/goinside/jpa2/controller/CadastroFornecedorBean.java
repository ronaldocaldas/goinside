package com.goinside.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.goinside.jpa2.model.Fornecedor;
import com.goinside.jpa2.service.CadastroFornecedorService;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFornecedorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2910681343548353180L;

	@Inject
	private CadastroFornecedorService cadastroFornecedorService;

	private Fornecedor fornecedor;

	@PostConstruct
	public void init() {
		this.limpar();
	}

	public void limpar() {
		this.fornecedor = new Fornecedor();
	}

	public void salvar() {
		try {
			this.cadastroFornecedorService.salvar(fornecedor);
			FacesUtil.addSuccessMessage("Fornecedor " + fornecedor.getNomeFantasia() + " salvo com sucesso!");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public CadastroFornecedorService getCadastroFornecedorService() {
		return cadastroFornecedorService;
	}

	public void setCadastroFornecedorService(CadastroFornecedorService cadastroFornecedorService) {
		this.cadastroFornecedorService = cadastroFornecedorService;
	}
}
