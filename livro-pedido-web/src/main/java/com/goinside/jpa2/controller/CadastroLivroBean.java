package com.goinside.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.goinside.jpa2.dao.FornecedorDAO;
import com.goinside.jpa2.model.Fornecedor;
import com.goinside.jpa2.model.Livro;
import com.goinside.jpa2.service.CadastroLivroService;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroLivroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6049201077111630380L;

	@Inject
	private CadastroLivroService cadastroLivroService;

	@Inject
	private FornecedorDAO fornecedorDAO;

	private Livro livro;

	private List<Fornecedor> fornecedores;

	@PostConstruct
	public void init() {
		this.limpar();
		this.fornecedores = fornecedorDAO.buscarTodos();
	}

	public void limpar() {
		this.livro = new Livro();
	}

	public void salvar() {
		try {
			this.cadastroLivroService.salvar(livro);
			FacesUtil.addSuccessMessage("Livro " + livro.getTitulo() + " salvo com sucesso!");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public boolean isModoEdicao(){
		return livro.getId() == 0 && livro.getIsbn() == 0; 
	}
	
	public String getTituloPagina(){
		if(isModoEdicao()) {
			return "Novo";
		}
		else return "Editar";
	}
}
