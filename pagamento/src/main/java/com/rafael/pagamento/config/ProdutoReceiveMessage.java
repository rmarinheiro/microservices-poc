package com.rafael.pagamento.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.rafael.pagamento.dto.ProdutoDTO;
import com.rafael.pagamento.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessage {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload ProdutoDTO produtoDTO) {
		produtoRepository.save(ProdutoDTO.create(produtoDTO));
		
		
	}

}
