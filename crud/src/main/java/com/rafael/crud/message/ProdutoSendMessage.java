package com.rafael.crud.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rafael.crud.DTO.ProductDTO;

@Component
public class ProdutoSendMessage {
	
	@Value("${crud.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${crud.rabbitmq.routingkey}")
	private String routingkey;
	
	public final RabbitTemplate rabbitTemplate;

	@Autowired
	public ProdutoSendMessage(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(ProductDTO dto) {
		rabbitTemplate.convertAndSend(exchange,routingkey,dto);
	}
	
	

}
