package co.edu.uniandes.dse.med4pet.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class CalificacionEntity extends BaseEntity
{
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
	 * Representa el veterinario que esta siendo calificado en la calificacion.
	 */
	@ManyToOne
	private VeterinarioEntity veterinarioCalificado;
	/**
	 * Representa el cliente que esta siendo calificado en la calificacion.
	 */
	@ManyToOne
	private ClienteEntity clienteCalificado;
	
}
