package com.rafael.crud.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.crud.DTO.ProductDTO;
import com.rafael.crud.service.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	private final PagedResourcesAssembler<ProductDTO> assembler = null;

	@GetMapping(value = "/{id}", produces = { "application/json" })
	public ProductDTO findById(@PathVariable("id") Long id) {
		ProductDTO productDTO = service.findById(id);
		productDTO.add(linkTo(methodOn(ProdutoController.class).findById(id)).withSelfRel());
		return productDTO;
	}

	@GetMapping()
	public ResponseEntity<?> findByAll( @RequestParam(value = "page", defaultValue = "0") int page,
										@RequestParam(value = "limt", defaultValue = "12") int limt,
										@RequestParam(value = "direction",defaultValue = "asc") String direction,
										@RequestParam(value = "orderBy", defaultValue = "nomeProduto") String orderBy) {

		//var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

		PageRequest pageable = PageRequest.of(page, limt, Sort.Direction.DESC,orderBy);
		Page<ProductDTO> produtos = service.findAll(pageable);
		produtos.stream()
				.forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).findById(p.getId())).withSelfRel()));

		//PagedModel<EntityModel<ProductDTO>> pageModel = assembler.toModel(produtos);
		return new ResponseEntity<>(produtos, HttpStatus.OK);

	}

	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
	public ProductDTO create(@RequestBody ProductDTO productDTO) {
		ProductDTO dto = service.create(productDTO);
		dto.add(linkTo(methodOn(ProdutoController.class).findById(dto.getId())).withSelfRel());
		return dto;
	}

	@PutMapping(value="/{id}", produces = { "application/json" }, consumes = { "application/json" })
	public ProductDTO update(@RequestBody ProductDTO productDTO , @PathVariable Long id) {
		ProductDTO dto = service.update(productDTO , id);
		dto.add(linkTo(methodOn(ProdutoController.class).findById(dto.getId())).withSelfRel());
		return dto;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
