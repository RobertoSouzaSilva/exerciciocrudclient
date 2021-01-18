package com.robertosouza.crudcliente.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robertosouza.crudcliente.dto.ClientDTO;
import com.robertosouza.crudcliente.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAllPaged(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ClientDTO> client = clientService.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(client);
	}

}
