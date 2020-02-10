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

import dev.fujioka.juliocesarviana.model.Cliente;
import dev.fujioka.juliocesarviana.repository.ClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Projeto Java Web")
@CrossOrigin(origins = "*")
public class ClienteController {

	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	@ApiOperation(value = "Retorna a lista de clientes")
	public List<Cliente> listaClientes(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/cliente/{id}")
	@ApiOperation(value = "Retorna cliente pelo ID")
	public Cliente listaClientenico(@PathVariable(value = "id") long id){
		return clienteRepository.findById(id);
	}

	@PostMapping("/cliente")
	@ApiOperation("Salva um cliente")
	public Cliente salvaCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	
	@DeleteMapping("/cliente/{id}")
	@ApiOperation(value = "Deleta um cliente")
	public void deletaCliente(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			clienteRepository.deleteById(id);
		}else {
			
		}
		
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/cliente/{id}")
	@ApiOperation(value = "Altera um cliente")
	public ResponseEntity<Cliente> atualizaCliente(@PathVariable ("id") Long id, @RequestBody Cliente cliente) {
		return clienteRepository.findById(id)
		.map(record -> {
            record.setNome(cliente.getNome());
            record.setEmail(cliente.getEmail());
            record.setTelefone(cliente.getTelefone());
            record.setIdade(cliente.getIdade());
            record.setValorComprado(cliente.getValorComprado());
            Cliente updated = clienteRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
}
	
}
