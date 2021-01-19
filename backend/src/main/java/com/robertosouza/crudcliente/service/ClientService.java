package com.robertosouza.crudcliente.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.robertosouza.crudcliente.dto.ClientDTO;
import com.robertosouza.crudcliente.entity.Client;
import com.robertosouza.crudcliente.repository.ClientRepository;
import com.robertosouza.crudcliente.service.exception.DatabaseException;
import com.robertosouza.crudcliente.service.exception.NotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> client = clientRepository.findAll(pageRequest);
		return client.map(cli -> new ClientDTO(cli));

	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		Client client = optionalClient.orElseThrow(() -> new NotFoundException("Id não encontrado"));
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO saveClient(ClientDTO dto) {
		Client client = new Client();
		return new ClientDTO(clientRepository.save(dtoToEntity(client, dto)));
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client client = clientRepository.getOne(id);
			return new ClientDTO(clientRepository.save(dtoToEntity(client, dto)));
		} catch (EntityNotFoundException e) {
			throw new NotFoundException("Id não encontrado");
		}
	}

	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("Id não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}

	}

	private Client dtoToEntity(Client client, ClientDTO dto) {
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
		return client;
	}

}
