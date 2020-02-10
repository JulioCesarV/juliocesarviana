package dev.fujioka.juliocesarviana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.fujioka.juliocesarviana.model.Produto;




@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Produto findById(long id);
	List<Produto> findProdutoByAnoFabricacao(int ano);
	
}
