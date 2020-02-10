package dev.fujioka.juliocesarviana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.juliocesarviana.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findById(long id);
	
}
