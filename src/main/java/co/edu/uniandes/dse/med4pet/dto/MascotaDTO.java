package co.edu.uniandes.dse.med4pet.dto;


import java.util.Date;

import lombok.Data;

@Data
public class MascotaDTO {

	private Long id;
	/**
	 * Representa el nombre de la mascota.
	 */
	private String nombre;
	
	/**
	 * Representa el raza de la mascota.
	 */
	private String raza;
	
	/**
	 * Representa la ubicacion de la mascota.
	 */
	private String ubicacion;
	
	/**
	 * Representa la fecha nacimiento de la mascota.
	 */
	private Date fechaNacimiento;
	
	
	//===========================================================================
	// Asociaciones
	//===========================================================================
	
	/**
	 * Representa al cliente duenio de la mascota.
	 */	

	//private ClienteDTO duenio;
	
	

}
