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

import dev.fujioka.juliocesarviana.model.Fornecedor;
import dev.fujioka.juliocesarviana.repository.FornecedorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Projeto Java Web")
@CrossOrigin(origins = "*")
public class FornecedorController {

	@Autowired
	FornecedorRepository fornecedorRepository;
	
	@GetMapping("/fornecedores")
	@ApiOperation(value = "Retorna a lista de fornecedores")
	public List<Fornecedor> listaFornecedores(){
		return fornecedorRepository.findAll();
	}
	
	@GetMapping("/fornecedor/{id}")
	@ApiOperation(value = "Retorna fornecedor pelo ID")
	public Fornecedor listaFornecedorUnico(@PathVariable(value = "id") long id){
		return fornecedorRepository.findById(id);
	}

	@PostMapping("/fornecedor")
	@ApiOperation("Salva um fornecedor")
	public Fornecedor salvaFornecedor(@RequestBody Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}
	
	
	@DeleteMapping("/fornecedor/{id}")
	@ApiOperation(value = "Deleta um fornecedor")
	public void deletaFornecedor(@PathVariable Long id) {
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
		if (fornecedor.isPresent()) {
			fornecedorRepository.deleteById(id);
		}else {
			
		}
		
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/fornecedor/{id}")
	@ApiOperation(value = "Altera um fornecedor")
	public ResponseEntity<Fornecedor> atualizaFornecedor(@PathVariable ("id") Long id, @RequestBody Fornecedor fornecedor) {
		return fornecedorRepository.findById(id)
		.map(record -> {
            record.setNome(fornecedor.getNome());
            record.setDescricaoFornecedor(fornecedor.getDescricaoFornecedor());
            record.setProduto(fornecedor.getProduto());
            record.setDescricaoProduto(fornecedor.getDescricaoProduto());
            record.setContato(fornecedor.getContato());
            record.setValorProduto(fornecedor.getValorProduto());
            Fornecedor updated = fornecedorRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
}
	
}

