package co.edu.uniandes.dse.med4pet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaDTO {
	
	//===========================================================================
	// Atributos
	//===========================================================================
	
	/**
	 * Identificador
	 */
	private Long id;
	
	/**
	 * Representa el número de todas las citas de la agenda que el cliente @cliente
	 * tiene a futuro.
	 */
	private Integer numeroCitasPendientes;
	
	/**
	 * Representa el número de todas las citas de la agenda que el cliente @cliente
	 * ha tomado anteriormente.
	 */
	private Integer numeroCitasRealizadas;
	
	/**
	 * Representa el número de todas las citas de la agenda del cliente @cliente que
	 * han sido canceladas anteriormente y a futuro.
	 */
	private Integer numeroCitasCanceladas;
	
}
