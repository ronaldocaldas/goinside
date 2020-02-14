package com.goinside.jpa2.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.goinside.jpa2.dao.FornecedorDAO;
import com.goinside.jpa2.model.Cliente;
import com.goinside.jpa2.model.Fornecedor;
import com.goinside.jpa2.model.Sexo;
import com.goinside.jpa2.service.CadastroClienteService;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6049201077111630380L;

	@Inject
	private CadastroClienteService cadastroClienteService;

	@Inject
	private FornecedorDAO fornecedorDAO;

	private Cliente cliente;

	private List<Fornecedor> fornecedores;
	
	private List<Sexo> sexos;


	@PostConstruct
	public void init() {
		this.limpar();
		this.fornecedores = fornecedorDAO.buscarTodos();
		this.sexos = Arrays.asList(Sexo.values());
	}

	public void limpar() {
		this.cliente = new Cliente();
	}

	public void salvar() {
		try {
			this.cadastroClienteService.salvar(cliente);
			FacesUtil.addSuccessMessage("Cliente " + cliente.getNome() + " salvo com sucesso!");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public List<Sexo> getSexos() {
		return sexos;
	}

	public void setSexos(List<Sexo> sexos) {
		this.sexos = sexos;
	}

	
	public boolean isEditando() {
		return this.cliente.getId() != null;
	}
	
	
}
