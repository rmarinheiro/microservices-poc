package com.rafael.crud.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import com.rafael.crud.DTO.ProductDTO;

@Entity
@Table(name="tbl_produto")
public class Produto implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nomeProduto" ,nullable = false , length = 255)
	private String nomeProduto;
	
	@Column(name="estoque" ,nullable = false , length = 10)
	private Integer estoque;
	
	@Column(name="preco" ,nullable = false , length = 10)
	private Double preco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(Long id, String nomeProduto, Integer estoque, Double preco) {
		super();
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.estoque = estoque;
		this.preco = preco;
	}
	
	public static Produto create(ProductDTO produtoDTO) {
		return new ModelMapper().map(produtoDTO, Produto.class);
	}
	
	

}
