package com.devmam.bookapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmam.bookapirest.domain.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

}

