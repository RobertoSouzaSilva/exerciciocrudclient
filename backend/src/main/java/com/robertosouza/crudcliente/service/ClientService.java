package com.robertosouza.crudcliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.robertosouza.crudcliente.dto.ClientDTO;
import com.robertosouza.crudcliente.entity.Client;
import com.robertosouza.crudcliente.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
		Page<Client> client = clientRepository.findAll(pageRequest);
		return client.map(cli -> new ClientDTO(cli));
		
	}

}
