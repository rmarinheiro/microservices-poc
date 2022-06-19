package com.rafael.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rafael.crud.DTO.ProductDTO;
import com.rafael.crud.entities.Produto;
import com.rafael.crud.exceptions.ResoruceNotFoundException;
import com.rafael.crud.message.ProdutoSendMessage;
import com.rafael.crud.message.ProdutoUpdateMessage;
import com.rafael.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private ProdutoSendMessage produtoSendMessage;
	
	@Autowired
	private  ProdutoUpdateMessage produtoUpdateMessage;
	
	public ProductDTO create(ProductDTO produtoDTO) {
		ProductDTO dto= ProductDTO.create(repository.save(Produto.create(produtoDTO)));
		produtoSendMessage.sendMessage(produtoDTO);
		return dto;
		
	}
	
	public Page<ProductDTO> findAll(Pageable pageable){
		var page = repository.findAll(pageable);
		return page.map(this::convertProductDTO);
		
	}
	
	
	private ProductDTO convertProductDTO(Produto produto) {
		return ProductDTO.create(produto);
	}
	
	public ProductDTO findById(Long id) {
		var entity = repository.findById(id)
					 .orElseThrow(()-> new ResoruceNotFoundException("Not Found for Id"));
		return ProductDTO.create(entity);
	}
	
	public ProductDTO update (ProductDTO productDTO, Long id) {
		Produto produto = repository.findById(id).get();
		produto.setEstoque(productDTO.getEstoque());
		produto.setNomeProduto(productDTO.getNomeProduto());
		produto.setPreco(productDTO.getPreco());
		Produto produtoNovo =repository.save(produto);
		//ProductDTO dto= productDTO.create(repository.save(produto.create(productDTO)));
		produtoUpdateMessage.sendMessage(productDTO);
		return productDTO.create(produtoNovo);
	}
	
	
	public void delete(Long id) {
		var entity = repository.findById(id)
				 .orElseThrow(()-> new ResoruceNotFoundException("Not Found for Id"));
		repository.delete(entity);
	}
	
	

}
