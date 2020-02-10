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

import dev.fujioka.juliocesarviana.model.Filial;
import dev.fujioka.juliocesarviana.repository.FilialRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Projeto Java Web")
@CrossOrigin(origins = "*")
public class FilialController {

	@Autowired
	FilialRepository filialRepository;
	
	@GetMapping("/filiais")
	@ApiOperation(value = "Retorna a lista de filiais")
	public List<Filial> listaFiliais(){
		return filialRepository.findAll();
	}
	
	@GetMapping("/filial/{id}")
	@ApiOperation(value = "Retorna filial pelo ID")
	public Filial listaFilialUnica(@PathVariable(value = "id") long id){
		return filialRepository.findById(id);
	}

	@PostMapping("/filial")
	@ApiOperation("Salva uma filial")
	public Filial salvaFilial(@RequestBody Filial filial) {
		return filialRepository.save(filial);
	}
	
	
	@DeleteMapping("/filial/{id}")
	@ApiOperation(value = "Deleta uma filial")
	public void deletaFilial(@PathVariable Long id) {
		Optional<Filial> filial = filialRepository.findById(id);
		if (filial.isPresent()) {
			filialRepository.deleteById(id);
		}else {
			
		}
		
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/filial/{id}")
	@ApiOperation(value = "Altera uma filial")
	public ResponseEntity<Filial> atualizaFilial(@PathVariable ("id") Long id, @RequestBody Filial filial) {
		return filialRepository.findById(id)
		.map(record -> {
            record.setNumero(filial.getNumero());
            record.setEndereco(filial.getEndereco());
            record.setFaturamentoMensal(filial.getFaturamentoMensal());
            record.setDespesaMensal(filial.getDespesaMensal());
            Filial updated = filialRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
}
	
}
