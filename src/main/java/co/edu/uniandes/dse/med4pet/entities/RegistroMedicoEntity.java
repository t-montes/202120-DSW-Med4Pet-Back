package co.edu.uniandes.dse.med4pet.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class RegistroMedicoEntity extends BaseEntity 
{
	/**
	 * Representa la identificacion del registro medico del veterinario.
	 */
	private String identificacion;
	/**
	 * Representa la fecha de expedicion del registro medico.
	 */
	private Date fechaExpedicion;
	/**
	 * Representa la ruta de acceso de la imagen del registro medico.
	 */
	private String imagen;
	/**
	 * Representa el veterinario que posee dicho registro medico.
	 */
	@OneToOne
	VeterinarioEntity veterinario;
	
	
}
