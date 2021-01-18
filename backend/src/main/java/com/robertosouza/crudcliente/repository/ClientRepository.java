package com.robertosouza.crudcliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robertosouza.crudcliente.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
