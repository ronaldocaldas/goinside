package com.goinside.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.goinside.jpa2.dao.ClienteDAO;
import com.goinside.jpa2.model.Cliente;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ListagemClientesBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7852286727330597069L;

	private List<Cliente> clientes;

	private Cliente clienteSelecionado;

	@Inject
	ClienteDAO clienteDAO;

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void excluir() {
		try {
			clienteDAO.excluir(clienteSelecionado);
			this.clientes.remove(clienteSelecionado);
			FacesUtil.addSuccessMessage("Cliente " + clienteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	@PostConstruct
	public void inicializar() {
		clientes = clienteDAO.buscarTodos();
	}

}
