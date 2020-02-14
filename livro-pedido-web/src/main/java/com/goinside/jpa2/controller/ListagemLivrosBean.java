package com.goinside.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.goinside.jpa2.dao.LivroDAO;
import com.goinside.jpa2.model.Livro;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ListagemLivrosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Livro> livros;

	private Livro livroSelecionado;

	@Inject
	LivroDAO livroDAO;

	public List<Livro> getLivros() {
		return livros;
	}

	public void excluir() {
		try {
			livroDAO.excluir(livroSelecionado);
			this.livros.remove(livroSelecionado);
			FacesUtil.addSuccessMessage("Modelo " + livroSelecionado.getTitulo() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}
	@PostConstruct
	public void inicializar() {
		livros = livroDAO.buscarTodos();
	}

}
