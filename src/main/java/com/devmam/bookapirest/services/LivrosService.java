package com.devmam.bookapirest.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devmam.bookapirest.domain.Comentario;
import com.devmam.bookapirest.domain.Livro;
import com.devmam.bookapirest.repository.ComentarioRepository;
import com.devmam.bookapirest.repository.LivrosRepository;
import com.devmam.bookapirest.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	//listar todos
	public List<Livro> listar() {
		return livrosRepository.findAll();
	}
	
	//buscar por ID	
	public Livro buscar(Long id) {
		Optional<Livro> livro = livrosRepository.findById(id);

		return livro.orElseThrow( () -> new LivroNaoEncontradoException(
				"O livro não foi encontrado em nosso Banco de Dados! ID. " + id + ", Tipo: "
		 + Livro.class.getName()));
	}

	
	//salvar
	public Livro salvar(Livro livro) {
		livro.setId(null);	
		
		return livrosRepository.save(livro);	
	}
	
	
	//deletar
	public void deletar(Long id) {				
		try {
			livrosRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não foi encontrado em nosso Banco de Dados!")	;		
		}
	}
	
	//atualizar
	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}
		
	private void verificarExistencia(Livro livro) { 
		buscar(livro.getId());										//public Optional<Livro> buscar(Long id)
	}
	
	//comentário
	
	public Comentario SalvarComentario(Long livroId, Comentario comentario) {
		Livro livro = buscar(livroId);
		
		comentario.setLivro(livro);
		comentario.setData(new Date());
		
		return comentarioRepository.save(comentario);
	}
	
		
	
}








