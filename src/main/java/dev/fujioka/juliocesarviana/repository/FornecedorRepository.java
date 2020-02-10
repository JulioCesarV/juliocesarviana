package dev.fujioka.juliocesarviana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.juliocesarviana.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	Fornecedor findById(long id);
	
}
