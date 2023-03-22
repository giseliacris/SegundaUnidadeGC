package com.programacaoorientada.trabalhogiselia.domain.model;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.programacaoorientada.trabalhogiselia.domain.ValidationGroups;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {
	@NotNull(groups = ValidationGroups.ClienteId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	@Size(max = 60)
    private String nome;
	@NotBlank
	@Email
	@Size(max=255)
   
	private String email;

    @NotBlank
    @Size(max=20)
    private String telefone;


}
