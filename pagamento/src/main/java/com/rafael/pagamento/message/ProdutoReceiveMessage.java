package com.rafael.pagamento.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.rafael.pagamento.dto.ProdutoDTO;
import com.rafael.pagamento.entity.Produto;
import com.rafael.pagamento.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessage {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload ProdutoDTO produtoDTO) {
		produtoRepository.save(ProdutoDTO.create(produtoDTO));
		
		
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue.update}"})
	public void update(@Payload ProdutoDTO produtoDTO) {
		Produto produto= produtoRepository.findByNomeProduto(produtoDTO.getNomeProduto());
		produto.setEstoque(produtoDTO.getEstoque());
		produtoRepository.save(produto);
		
		
	}

}
