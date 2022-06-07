package com.rafael.pagamento.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.rafael.pagamento.dto.ProdutoVendaDTO;
import com.rafael.pagamento.dto.VendaDTO;

@Entity
@Table(name = "tbl_venda")
public class Venda implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "data",nullable = false)
	private Date data;
	
	@Column(name = "valorTotal",nullable = false)
	private Double valorTotal;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "venda",cascade = {CascadeType.REFRESH})
	private List<ProdutoVenda> listaProdutos = new ArrayList<>();

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

	

	

	public List<ProdutoVenda> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<ProdutoVenda> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public static Venda create(VendaDTO vendaDTO) {
		return new ModelMapper().map(vendaDTO,Venda.class); 
	}

	
	

}
