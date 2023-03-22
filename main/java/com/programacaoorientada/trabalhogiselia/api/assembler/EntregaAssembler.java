package com.programacaoorientada.trabalhogiselia.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.programacaoorientada.trabalhogiselia.api.model.EntregaModel;
import com.programacaoorientada.trabalhogiselia.api.model.input.EntregaInput;
import com.programacaoorientada.trabalhogiselia.domain.model.Entrega;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Component
public class EntregaAssembler {
 
	private ModelMapper modelMapper;
	
	public EntregaModel toModel(Entrega entrega) {
		
		return modelMapper.map(entrega,EntregaModel.class);
	}
	
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
		      return entregas.stream()
		            .map(this::toModel)
		            .collect(Collectors.toList());
		
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		
		return modelMapper.map(entregaInput, Entrega.class);
	}
	
}
