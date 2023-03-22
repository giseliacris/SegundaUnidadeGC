package com.programacaoorientada.trabalhogiselia.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programacaoorientada.trabalhogiselia.domain.exception.NegocioException;
import com.programacaoorientada.trabalhogiselia.domain.model.Cliente;
import com.programacaoorientada.trabalhogiselia.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class CatalogoClienteService {

	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		 return clienteRepository.findById(clienteId)
				   .orElseThrow(()->new NegocioException("Cliente nao encontrado"));
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
		         .stream()
		         .anyMatch(clienteExistente->!clienteExistente.equals(cliente));
		if(emailEmUso) {
			throw new NegocioException("Já existe um usuario cadastrado com esse e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId){
		clienteRepository.deleteById(clienteId);
		
	}
}
