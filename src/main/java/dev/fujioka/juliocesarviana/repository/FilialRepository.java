package dev.fujioka.juliocesarviana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.juliocesarviana.model.Filial;

public interface FilialRepository extends JpaRepository<Filial, Long> {

	Filial findById(long id);
	
}
