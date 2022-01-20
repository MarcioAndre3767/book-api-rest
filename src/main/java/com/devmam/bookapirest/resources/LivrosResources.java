package com.devmam.bookapirest.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devmam.bookapirest.domain.Livro;
import com.devmam.bookapirest.repository.LivrosRepository;


@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Livro> listar() {
		return livrosRepository.findAll();		
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public void salvar(@RequestBody Livro livro) {
		livrosRepository.save(livro);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Livro> buscar(@PathVariable("id") Long id) {
		Optional<Livro> livroID = livrosRepository.findById(id);		
		return livroID;
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletar(@PathVariable("id") Long id) {
		if( livrosRepository.existsById(id) ) {
			livrosRepository.deleteById(id);
		}
				
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosRepository.save(livro);
		
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
