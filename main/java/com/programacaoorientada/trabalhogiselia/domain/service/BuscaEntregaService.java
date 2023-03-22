package com.programacaoorientada.trabalhogiselia.domain.service;
import org.springframework.stereotype.Service;

import com.programacaoorientada.trabalhogiselia.domain.exception.EntidadeNaoEncontradaException;
import com.programacaoorientada.trabalhogiselia.domain.exception.NegocioException;
import com.programacaoorientada.trabalhogiselia.domain.model.Entrega;
import com.programacaoorientada.trabalhogiselia.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(()-> new EntidadeNaoEncontradaException("Entrega não encontrada") );
	}
}
