package dev.fujioka.juliocesarviana.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.juliocesarviana.model.Usuario;
import dev.fujioka.juliocesarviana.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuarios")
	@ApiOperation(value = "Retorna a lista de usuarios")
	public List<Usuario> listaUsuario(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/usuario/{id}")
	@ApiOperation(value = "Retorna usuario pelo ID")
	public Usuario listaUsuarioUnico(@PathVariable(value = "id") long id){
		return usuarioRepository.findById(id);
	}

	@PostMapping("/usuario")
	@ApiOperation("Salva um usuario")
	public Usuario salvaUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	
	@DeleteMapping("/usuario/{id}")
	@ApiOperation(value = "Deleta um usuario")
	public void deletaUsuario(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			usuarioRepository.deleteById(id);
		}else {
			
		}
		
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/usuario/{id}")
	@ApiOperation(value = "Altera um usuario")
	public ResponseEntity<Usuario> atualizaUsuario(@PathVariable ("id") Long id, @RequestBody Usuario usuario) {
		return usuarioRepository.findById(id)
		.map(record -> {
            record.setUsername(usuario.getUsername());
            record.setSenha(usuario.getSenha());
            record.setPerfil(usuario.getPerfil());
            record.setIdade(usuario.getIdade());
            record.setTempoOnline(usuario.getTempoOnline());
            Usuario updated = usuarioRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
}
	
}
