package co.edu.uniandes.dse.med4pet.dto;

import lombok.Data;

@Data
public class VeterinarioDTO {
	
	//===========================================================================
	// Atributos
	//===========================================================================
		
	private Long id;
	/**
	 * Representa el nombre del veterinario
	 */
	private String nombre;
	/**
	 * Representa la especialidad del veterinario.
	 */
	private String especialidad;
	/**
	 * Representa la direccion del certificado de entrenamiento del veterinario.
	 */
	private String certificadoEntrenamiento;
	/**
	 * Representa la experiencia previa del veterinario.
	 */
	private String experienciaPrevia;
	/**
	 * Representa la calificacion promedio del veterinario.
	 */
	private Double calificacion;
	
	//===========================================================================
	// Asociaciones
	//===========================================================================
	
	/**
	 * Representa la informacion de contacto del veterinario
	 */
	private ContactoDTO contacto;
	/**
	 * Representa el registro medico del veterinario.
	 */
	private RegistroMedicoDTO registroMedico;
	/**
	 * Representa la agenda del veterinario.
	 */
	private AgendaDTO agenda;

}
