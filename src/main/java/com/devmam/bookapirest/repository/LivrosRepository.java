package com.devmam.bookapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmam.bookapirest.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

	
	

}
