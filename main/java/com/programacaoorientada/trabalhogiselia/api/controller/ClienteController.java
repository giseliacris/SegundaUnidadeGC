package com.programacaoorientada.trabalhogiselia.api.controller;
//import java.util.Arrays;

import java.util.List;
//import java.util.Optional;

import javax.validation.Valid;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.programacaoorientada.trabalhogiselia.domain.model.*;
import com.programacaoorientada.trabalhogiselia.domain.repository.ClienteRepository;
import com.programacaoorientada.trabalhogiselia.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor 
@RequestMapping("/clientes")
public class ClienteController {
	
	
	//@Autowired
	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	
	
    @GetMapping
	public List<Cliente> listar() {
		
    	return clienteRepository.findAll();
	}
    
    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId ){
    return clienteRepository.findById(clienteId)
    	   .map(cliente->ResponseEntity.ok(cliente))
    	   .orElse(ResponseEntity.notFound().build());
   
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
    	//return clienteRepository.save(cliente);
    	return catalogoClienteService.salvar(cliente);
    }
    
    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
    	if(!clienteRepository.existsById(clienteId)) {
    		return ResponseEntity.notFound().build();
    	}
    	cliente.setId(clienteId);
    	//cliente = clienteRepository.save(cliente);
    	cliente = catalogoClienteService.salvar(cliente);
    	
    	return ResponseEntity.ok(cliente);
    }
    
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Cliente> remover(@PathVariable Long clienteId){
    	if(!clienteRepository.existsById(clienteId)) {
    		return ResponseEntity.notFound().build();
    	}
    	//clienteRepository.deleteById(clienteId);
    	
    	catalogoClienteService.excluir(clienteId);
    	
    	return ResponseEntity.noContent().build();
    }
    
    
}
