package com.devmam.bookapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmam.bookapirest.domain.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
