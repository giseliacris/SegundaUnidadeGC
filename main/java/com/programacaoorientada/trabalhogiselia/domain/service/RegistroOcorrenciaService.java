package com.programacaoorientada.trabalhogiselia.domain.service;

//import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import com.programacaoorientada.trabalhogiselia.domain.exception.NegocioException;
import com.programacaoorientada.trabalhogiselia.domain.model.Entrega;
import com.programacaoorientada.trabalhogiselia.domain.model.Ocorrencia;
//import com.programacaoorientada.trabalhogiselia.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class RegistroOcorrenciaService {
    
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		    return  entrega.adicionarOcorrencia(descricao);
		
	}
}
