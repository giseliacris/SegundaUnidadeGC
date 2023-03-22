package com.programacaoorientada.trabalhogiselia.api.controller;

import java.util.List;


import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.programacaoorientada.trabalhogiselia.api.assembler.EntregaAssembler;
import com.programacaoorientada.trabalhogiselia.api.model.DestinatarioModel;
import com.programacaoorientada.trabalhogiselia.api.model.EntregaModel;
import com.programacaoorientada.trabalhogiselia.api.model.input.EntregaInput;
import com.programacaoorientada.trabalhogiselia.domain.model.Entrega;
import com.programacaoorientada.trabalhogiselia.domain.repository.EntregaRepository;
import com.programacaoorientada.trabalhogiselia.domain.service.FinalizacaoEntregaServise;
import com.programacaoorientada.trabalhogiselia.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
    
	private EntregaRepository entregaRepository ;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private FinalizacaoEntregaServise  finalizacaoEntregaServise;
	private EntregaAssembler entregaAssembler;
     
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
     public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
    	Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		return entregaAssembler.toModel(entregaSolicitada);
     }
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaServise.finalizar(entregaId);
		
	}
	
	@GetMapping
	public List<EntregaModel> listar(){
		return entregaAssembler.toCollectionModel(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
		return entregaRepository.findById(entregaId)
				.map(entrega-> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
}
