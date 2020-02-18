package com.goinside.jpa2.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.goinside.jpa2.model.Cliente;
import com.goinside.jpa2.model.ItemPedido;
import com.goinside.jpa2.model.Livro;
import com.goinside.jpa2.model.Pedido;
import com.goinside.jpa2.model.Vendedor;
import com.goinside.jpa2.service.CadastroItemPedidoService;
import com.goinside.jpa2.service.NegocioException;
import com.goinside.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8309206603566172637L;

	@Inject
	private CadastroPedidoService cadastroPedidoService;

	@Inject
	private CadastroItemPedidoService cadastroItemPedidoService;

	private Cliente clienteSelecionado;
	private Vendedor vendedorSelecionado;
	private Livro livroSelecionado;
	private ItemPedido itemPedidoSelecionado;
	private Pedido pedidoSelecionado;
	private List<ItemPedido> listaItens = new ArrayList<ItemPedido>();
	private List<ItemPedido> itemsDetalhe = new ArrayList<ItemPedido>();
	private List<Cliente> todosClientes;
	private List<Vendedor> todosVendedores;
	private List<Livro> todosLivros;
	private List<Pedido> todosPedidos;

	private float valorTotal;

	@PostConstruct
	public void init() {
		this.todosClientes = cadastroItemPedidoService.getTodosClientes();
		this.todosVendedores = cadastroItemPedidoService.getTodosVendedores();
		this.todosLivros = cadastroItemPedidoService.getTodosLivros();
		this.todosPedidos = cadastroPedidoService.getTotosPedidos();
	}

	public void adicionaPedido() {
		ItemPedido item = new ItemPedido();
		item.setDataPedido(new Date());
		item.setCliente(this.clienteSelecionado);
		item.setVendedor(this.vendedorSelecionado);
		item.setLivro(this.livroSelecionado);
		this.listaItens.add(item);
		limparItem();
	}
	
	public void excluirPedido() throws NegocioException{
		if(this.pedidoSelecionado != null){
			this.cadastroPedidoService.excluir(this.pedidoSelecionado);
		}
	}
	
	private void limparItem() {
		this.clienteSelecionado = new Cliente();
		this.vendedorSelecionado = new Vendedor();
		this.livroSelecionado = new Livro();
	}

	public int getTamanhoListaItem() {
		return this.listaItens.size();
	}
	
	public void buscaItemsDetalhe(){
		this.itemsDetalhe = cadastroItemPedidoService.buscarItensPorPedido(pedidoSelecionado.getId());
	}
	
	public BigDecimal getTotalPedido() {
		BigDecimal total = BigDecimal.ZERO;
		if (isListaNaoVazia()) {
			for (ItemPedido itemPedido : listaItens) {
				total = total.add(itemPedido.getLivro().getValor());
			}
		}
		return total;
	}

	public boolean isListaNaoVazia() {
		return this.listaItens != null && !this.listaItens.isEmpty();
	}

	public void limparPedido() {
		listaItens.clear();
	}

	public String finalizaPedido() throws NegocioException {
		if (isListaNaoVazia()) {
			Pedido novoPedido = new Pedido();
			novoPedido.setDataPedido(new Date());
			novoPedido.setItems(listaItens);
			cadastroPedidoService.salvar(novoPedido);
			FacesUtil.addSuccessMessage("Pedido registrado com sucesso!");
			limparPedido();
		}
		return null;
	}

	public String excluirItemPedido() {

		if (isListaNaoVazia()) {
			if (this.itemPedidoSelecionado != null) {
				this.listaItens.remove(this.itemPedidoSelecionado);
			}
		}

		return null;
	}

	public List<Cliente> buscaCliente(String nome) {
		List<Cliente> busca = new ArrayList<>();
		if (this.todosClientes != null && !this.todosClientes.isEmpty()) {
			for (Cliente cliente : todosClientes) {
				if (cliente.getNome().toUpperCase().contains(nome.toUpperCase())) {
					busca.add(cliente);
				}
			}
		}
		return busca;
	}

	public List<Vendedor> buscaVendedor(String nome) {
		List<Vendedor> busca = new ArrayList<>();
		if (this.todosVendedores != null && !this.todosVendedores.isEmpty()) {
			for (Vendedor vendedor : todosVendedores) {
				if (vendedor.getNome().toUpperCase().contains(nome.toUpperCase())) {
					busca.add(vendedor);
				}
			}
		}
		return busca;
	}

	public List<Livro> buscaLivro(String nome) {
		List<Livro> busca = new ArrayList<>();
		if (this.todosLivros != null && !this.todosLivros.isEmpty()) {
			for (Livro livro : todosLivros) {
				if (livro.getTitulo().toUpperCase().contains(nome.toUpperCase()) && livro.getEstoque()>0) {
					busca.add(livro);
				}
			}
		}
		return busca;
	}

	public CadastroPedidoService getCadastroPedidoService() {
		return cadastroPedidoService;
	}

	public void setCadastroPedidoService(CadastroPedidoService cadastroPedidoService) {
		this.cadastroPedidoService = cadastroPedidoService;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Vendedor getVendedorSelecionado() {
		return vendedorSelecionado;
	}

	public void setVendedorSelecionado(Vendedor vendedorSelecionado) {
		this.vendedorSelecionado = vendedorSelecionado;
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}

	public ItemPedido getItemPedidoSelecionado() {
		return itemPedidoSelecionado;
	}

	public void setItemPedidoSelecionado(ItemPedido itemPedidoSelecionado) {
		this.itemPedidoSelecionado = itemPedidoSelecionado;
	}

	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(Pedido pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}

	public List<ItemPedido> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ItemPedido> listaItens) {
		this.listaItens = listaItens;
	}

	public List<ItemPedido> getItemsDetalhe() {
		return itemsDetalhe;
	}

	public void setItemsDetalhe(List<ItemPedido> itemsDetalhe) {
		this.itemsDetalhe = itemsDetalhe;
	}

	public List<Cliente> getTodosClientes() {
		return todosClientes;
	}

	public void setTodosClientes(List<Cliente> todosClientes) {
		this.todosClientes = todosClientes;
	}

	public List<Vendedor> getTodosVendedores() {
		return todosVendedores;
	}

	public void setTodosVendedores(List<Vendedor> todosVendedores) {
		this.todosVendedores = todosVendedores;
	}

	public List<Livro> getTodosLivros() {
		return todosLivros;
	}

	public void setTodosLivros(List<Livro> todosLivros) {
		this.todosLivros = todosLivros;
	}

	public List<Pedido> getTodosPedidos() {
		return todosPedidos;
	}

	public void setTodosPedidos(List<Pedido> todosPedidos) {
		this.todosPedidos = todosPedidos;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

}
