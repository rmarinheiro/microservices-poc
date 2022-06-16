package com.rafael.pagamento.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_produto")
public class Produto {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(name = "estoque" , nullable = false ,length = 10)
	private Integer estoque;

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
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(Long id, Integer estoque) {
		super();
		this.id = id;
		this.estoque = estoque;
	}

}
