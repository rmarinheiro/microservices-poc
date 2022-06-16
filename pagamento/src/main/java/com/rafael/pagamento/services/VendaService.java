package com.rafael.pagamento.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rafael.pagamento.dto.VendaDTO;
import com.rafael.pagamento.entity.Produto;
import com.rafael.pagamento.entity.ProdutoVenda;
import com.rafael.pagamento.entity.Venda;
import com.rafael.pagamento.exceptions.ResourceNotFoundException;
import com.rafael.pagamento.repository.ProdutoRepository;
import com.rafael.pagamento.repository.ProdutoVendaRepository;
import com.rafael.pagamento.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private  ProdutoVendaRepository produtoVendaRepository;

	public VendaDTO create(VendaDTO vendaDTO) {
		Venda venda = vendaRepository.save(Venda.create(vendaDTO));
		List<ProdutoVenda> produtosSalvos = new ArrayList<>();
		vendaDTO.getListaProdutosVenda().forEach(p-> { 
			ProdutoVenda pv =  ProdutoVenda.create(p);
			pv.setVenda(venda);
			produtosSalvos.add(produtoVendaRepository.save(pv));
		});
		venda.setListaProdutos(produtosSalvos);
		
		return vendaDTO.create(venda);

	}

	public Page<VendaDTO> findAll(Pageable pageable) {
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertVendaDTO);

	}

	private VendaDTO convertVendaDTO(Venda venda) {
		return VendaDTO.create(venda);
	}
	
	public VendaDTO findById(Long id) {
		var entity = vendaRepository.findById(id)
					 .orElseThrow(()-> new ResourceNotFoundException("Not Found for Id"));
		return VendaDTO.create(entity);
	}

}
