package com.programacaoorientada.trabalhogiselia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.programacaoorientada.trabalhogiselia.domain.model.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

	
	
	
	
}
