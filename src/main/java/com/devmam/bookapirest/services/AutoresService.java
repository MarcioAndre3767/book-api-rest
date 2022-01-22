package com.devmam.bookapirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devmam.bookapirest.domain.Autor;
import com.devmam.bookapirest.repository.AutoresRepository;
import com.devmam.bookapirest.services.exceptions.AutorExistenteException;
import com.devmam.bookapirest.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {
	
	@Autowired	
	private AutoresRepository autoresRepository;

	
	public List<Autor> listar() {
		return autoresRepository.findAll();
	}
	
	public Autor salvar(Autor autor) {
		if(autor.getId() != null) {
			Autor a = autoresRepository.getById(autor.getId());
			
			if (a != null) {
				throw new AutorExistenteException("O autor já existe no Banco de Dados!");
			}
		}
				
		return autoresRepository.save(autor);		
	}
	
	
	//buscar autor por id
	public Autor buscar(Long id) {
		Optional<Autor> autor = autoresRepository.findById(id);

		return autor.orElseThrow( () -> new AutorNaoEncontradoException(
				"O autor não foi encontrado em nosso Banco de Dados! ID. " + id + ", Tipo: "
		 + Autor.class.getName()));
	}
	

		
	//pr
}










