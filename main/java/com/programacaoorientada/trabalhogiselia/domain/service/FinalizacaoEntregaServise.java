package com.programacaoorientada.trabalhogiselia.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programacaoorientada.trabalhogiselia.domain.model.Entrega;
//import com.programacaoorientada.trabalhogiselia.domain.model.StatusEntrega;
import com.programacaoorientada.trabalhogiselia.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaServise {
	private EntregaRepository entregaRepository;
    private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		 
		entrega.finalizar();
		
		entregaRepository.save(entrega);
	}
}
