package dev.fujioka.juliocesarviana.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.juliocesarviana.model.Produto;
import dev.fujioka.juliocesarviana.repository.ProdutoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(value = "/api")
@Api(value = "API Projeto Java Web")
@CrossOrigin(origins = "*")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/produtos")
	@ApiOperation(value = "Retorna a lista de produtos")
	public List<Produto> listaProdutos(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("/produto/{id}")
	@ApiOperation(value = "Retorna produto pelo ID")
	public Produto listaProdutoUnico(@PathVariable(value = "id") long id){
		return produtoRepository.findById(id);
	}

	@PostMapping("/produto")
	@ApiOperation("Salva um produto")
	public Produto salvaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	
	@DeleteMapping("/produto/{id}")
	@ApiOperation(value = "Deleta um produto")
	public void deletaProduto(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			produtoRepository.deleteById(id);
		}else {
			
		}
		
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/produto/{id}")
	@ApiOperation(value = "Altera um produto")
	public ResponseEntity<Produto> atualizaProduto(@PathVariable ("id") Long id, @RequestBody Produto produto) {
		return produtoRepository.findById(id)
		.map(record -> {
            record.setNome(produto.getNome());
            record.setMarca(produto.getMarca());
            record.setAnoFabricacao(produto.getAnoFabricacao());
            record.setValor(produto.getValor());
            Produto updated = produtoRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
}
	
}
