package com.rafael.pagamento.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.ui.ModelMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rafael.pagamento.entity.Produto;

@JsonPropertyOrder({ "id", "estoque","nomeProduto","preco"})
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> implements Serializable {

	
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("estoque")
	private Integer estoque;
	
	@JsonProperty("nomeProduto")
	private String nomeProduto;
	
	@JsonProperty("preco")
	private Double preco;


	

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
	public ProdutoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public static ProdutoDTO create(Produto produto) {
		return new ModelMapper().map(produto, ProdutoDTO.class);
	}
	
	public static Produto create(ProdutoDTO produto) {
		return new ModelMapper().map(produto, Produto.class);
	}

}
