package com.devmam.bookapirest.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devmam.bookapirest.domain.Livro;
import com.devmam.bookapirest.repository.LivrosRepository;


@RestController
@RequestMapping("/livros")
public class LivrosResources {
		
	@Autowired
	private LivrosRepository livrosRepository;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosRepository.findAll());		
	}
		
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		livro = livrosRepository.save(livro);
				
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()		//retorno com URI
				.path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	} 
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Livro> buscar(@PathVariable("id") Long id) {
		return livrosRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}	
		
	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Livro> buscar(@PathVariable("id") Long id) {
		Optional<Livro> livroID = livrosRepository.findById(id);		
		return livroID;
	}*/
	 
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		
		try {
			livrosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();			
		}
		
		return ResponseEntity.noContent().build();		
	}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosRepository.save(livro);
		
		return ResponseEntity.noContent().build();
		
	}

	

		

}


/*
@DeleteMapping("/{id}")
public ResponseEntity<Void> remover(@PathVariable Long id) {
	if( !livrosRepository.existsById(id) ) {
		return ResponseEntity.notFound().build();
	}	
	livrosRepository.deleteById(id);
	
	return ResponseEntity.noContent().build();
		
}
*/

/*

@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
public void deletar(@PathVariable("id") Long id) {
	livrosRepository.delete(id);
}



@RequestMapping(value = "/{id}", method = RequestMethod.GET)
public ResponseEntity<Livro> buscar(@PathVariable("id") Long id) {
	return livrosRepository.findById(id)
	.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}
*/
