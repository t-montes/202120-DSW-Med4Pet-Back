package co.edu.uniandes.dse.med4pet.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter

public class CalificacionEntity extends BaseEntity
{
	//===========================================================================
	// Atributos
	//===========================================================================

	/**
	 * Representa el creador de la calificacion.
	 */
	private String creador;
	/**
	 * Representa el puntaje de calificacion
	 */
	private Double puntaje;
	/**
	 * Representa la fecha de la calificacion.
	 */
	private Date fecha;
	/**
	 * Representa la descripcion de la calificacion.
	 */
	private String descripcion;
	
	
	//===========================================================================
	// Asociaciones
	//===========================================================================

	/**
	 * Representa el veterinario que esta siendo calificado en la calificacion.
	 */
	@PodamExclude
	@ManyToOne
	private VeterinarioEntity veterinarioCalificado;
	/**
	 * Representa el cliente que esta siendo calificado en la calificacion.
	 */
	@PodamExclude
	@ManyToOne
	private ClienteEntity clienteCalificado;
	
}
