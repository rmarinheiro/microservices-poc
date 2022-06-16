package com.rafael.pagamento.dto;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "idProduto","qtdProdutos"})
public class ProdutoVendaDTO extends RepresentationModel<ProdutoVendaDTO> implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("idProduto")
	private Long idProduto;
	
	@JsonProperty("qtdProdutos")
	private Integer qtdProdutos;
	
	

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
	
	public ProdutoVendaDTO() {
	}
	
	public static ProdutoVendaDTO create(ProdutoVendaDTO produto) {
		return new ModelMapper().map(produto, ProdutoVendaDTO.class);
	}

	
}
