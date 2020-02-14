package com.goinside.jpa2.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.goinside.jpa2.model.Sexo;
import com.goinside.jpa2.model.Vendedor;
import com.goinside.jpa2.service.CadastroVendedorService;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroVendedorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6049201077111630380L;

	@Inject
	private CadastroVendedorService cadastroVendedorService;

	private Vendedor vendedor;
	private List<Sexo> sexos;


	@PostConstruct
	public void init() {
		this.limpar();
		this.sexos = Arrays.asList(Sexo.values());
	}

	public void limpar() {
		this.vendedor = new Vendedor();
	}

	public void salvar() {
		try {
			this.cadastroVendedorService.salvar(vendedor);
			FacesUtil.addSuccessMessage("Vendedor " + vendedor.getNome() + " salvo com sucesso!");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}

	public void setSexos(List<Sexo> sexos) {
		this.sexos = sexos;
	}

	
	public boolean isEditando() {
		return this.vendedor.getId() != null;
	}
	
	
}
