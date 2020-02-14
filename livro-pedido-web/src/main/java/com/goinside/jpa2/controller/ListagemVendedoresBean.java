package com.goinside.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.goinside.jpa2.dao.VendedorDAO;
import com.goinside.jpa2.model.Vendedor;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ListagemVendedoresBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7852286727330597069L;

	private List<Vendedor> vendedores;

	private Vendedor vendedorSelecionado;

	@Inject
	VendedorDAO vendedorDAO;

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void excluir() {
		try {
			vendedorDAO.excluir(vendedorSelecionado);
			this.vendedores.remove(vendedorSelecionado);
			FacesUtil.addSuccessMessage("Vendedor " + vendedorSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Vendedor getVendedorSelecionado() {
		return vendedorSelecionado;
	}

	public void setVendedorSelecionado(Vendedor vendedorSelecionado) {
		this.vendedorSelecionado = vendedorSelecionado;
	}
	@PostConstruct
	public void inicializar() {
		vendedores = vendedorDAO.buscarTodos();
	}

}
