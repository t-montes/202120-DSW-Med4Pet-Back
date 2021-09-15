package co.edu.uniandes.dse.med4pet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
	
	//===========================================================================
	// Atributos
	//===========================================================================
	
	/**
	 * Identificador
	 */
	private Long id;
	/**
	 * Representa el nombre del cliente.
	 */
	private String nombre;
	
	/**
	 * Representa la calificacion dada al cliente.
	 */
	private Double calificacion;
		

}
