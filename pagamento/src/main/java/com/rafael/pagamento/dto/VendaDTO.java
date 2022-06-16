package com.rafael.pagamento.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rafael.pagamento.entity.ProdutoVenda;
import com.rafael.pagamento.entity.Venda;

@JsonPropertyOrder({ "id", "data", "valorTotal", "produtos" })
public class VendaDTO extends RepresentationModel<VendaDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("data")
	private Date data;

	@JsonProperty("valorTotal")
	private Double valorTotal;

	@JsonProperty("produtos")
	private List<ProdutoVendaDTO> listaProdutosVenda = new ArrayList<>();

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	

	public List<ProdutoVendaDTO> getListaProdutosVenda() {
		return listaProdutosVenda;
	}

	public void setListaProdutosVenda(List<ProdutoVendaDTO> listaProdutosVenda) {
		this.listaProdutosVenda = listaProdutosVenda;
	}

	public VendaDTO() {
		// TODO Auto-generated constructor stub
	}

	public VendaDTO(Long id, Date data, Double valorTotal) {
		super();
		this.id = id;
		this.data = data;
		this.valorTotal = valorTotal;
	}
	
	public static VendaDTO create(Venda venda) {
		return new ModelMapper().map(venda, VendaDTO.class);
	}
}
