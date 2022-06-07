package com.rafael.pagamento.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.rafael.pagamento.dto.ProdutoDTO;
import com.rafael.pagamento.dto.ProdutoVendaDTO;

@Entity
@Table(name = "tbl_produtovenda")
public class ProdutoVenda implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="idProduto",nullable = false, length = 10)
	private Long idProduto;
	
	@Column(name="qtdProdutos",nullable = false, length = 10)
	private Integer qtdProdutos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_venda")
	private Venda venda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getQtdProdutos() {
		return qtdProdutos;
	}

	public void setQtdProdutos(Integer qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public ProdutoVenda() {
		// TODO Auto-generated constructor stub
	}

	public ProdutoVenda(Long id, Long idProduto, Integer qtdProdutos, Venda venda) {
		super();
		this.id = id;
		this.idProduto = idProduto;
		this.qtdProdutos = qtdProdutos;
		this.venda = venda;
	}
	
	public static ProdutoVenda create(ProdutoVendaDTO produtoVendaDTO) {
		return new ModelMapper().map(produtoVendaDTO, ProdutoVenda.class);
	}
	
	

}
