package com.rafael.pagamento.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.pagamento.dto.VendaDTO;
import com.rafael.pagamento.services.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaController {
	
	@Autowired
	private VendaService service;
	
	private final PagedResourcesAssembler<VendaDTO> assembler = null;


	@GetMapping(value = "/{id}", produces = { "application/json" })
	public VendaDTO findById(@PathVariable("id") Long id) {
		VendaDTO productDTO = service.findById(id);
		productDTO.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
		return productDTO;
	}

	@GetMapping()
	public ResponseEntity<?> findByAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limt", defaultValue = "12") int limt,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			@RequestParam(value = "orderBy", defaultValue = "valorTotal") String orderBy) {

		// var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC :
		// Direction.ASC;

		PageRequest pageable = PageRequest.of(page, limt, Sort.Direction.DESC, orderBy);
		Page<VendaDTO> produtos = service.findAll(pageable);
		produtos.stream()
				.forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));

		// PagedModel<EntityModel<VendaDTO>> pageModel = assembler.toModel(produtos);
		return new ResponseEntity<>(produtos, HttpStatus.OK);

	}

	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
	public VendaDTO create(@RequestBody VendaDTO productDTO) {
		VendaDTO dto = service.create(productDTO);
		dto.add(linkTo(methodOn(VendaController.class).findById(dto.getId())).withSelfRel());
		return dto;
	}

}
