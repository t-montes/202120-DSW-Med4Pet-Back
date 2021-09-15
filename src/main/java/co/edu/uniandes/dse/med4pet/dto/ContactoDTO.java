package co.edu.uniandes.dse.med4pet.dto;

import lombok.Data;

@Data
public class ContactoDTO {

	//===========================================================================
	// Atributos
	//===========================================================================
		
	private Long id;
	/**
	 * Representa el telefono del contacto
	 */
	private String telefono;
	/**
	 * Representa el correo del contacto
	 */
	private String correo;
	
}
