package co.edu.uniandes.dse.med4pet.dto;

import lombok.Data;

@Data
public abstract class MedioDePagoDTO {
	
	// Asociación del otro lado con ClienteDTO
	private String nombre;
	private Long id;
}
