package co.edu.uniandes.dse.med4pet.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter

public class VeterinarioEntity extends BaseEntity{
	
	//===========================================================================
	// Atributos
	//===========================================================================

	/**
	 * Representa el Nombre del Veterinario
	 */
	private String nombre;
	/**
	 * Representa la especialidad que tiene el veterinario
	 */
	private String especialidad;
	/**
	 * Representa la experiencia de donde ha trabajado previamente un veterinario
	 */
	private	String experienciaPrevia;
	/**
	 * Representa la ruta del archivo para el certificado de entrenamiento del veterinario
	 */
	private String certificadoEntrenamiento;
	/**
	 * Representa la calificacion promedio del veterinario
	 */
	private Double calificacion;
	
	//===========================================================================
	// Asociaciones
	//===========================================================================
	
	/**
	 * Representa la agenda con la lista de citas que ha creado el veterinario.
	 */
	@PodamExclude
	@OneToOne(mappedBy="veterinario")
	private AgendaEntity agenda;
	
	/**
	 * Representa el registroMedico de un veterinario.
	 */
	@PodamExclude
	@OneToOne(mappedBy ="veterinario", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private RegistroMedicoEntity registroMedico;
	/**
	 * Representa el contacto asociado a un veterinario.
	 */
	@PodamExclude
	@OneToOne(mappedBy ="veterinario")
	private ContactoEntity contacto;

	/**
	 * Representa la lista de las calificaciones de un veterinario.
	 */
	@PodamExclude
	@OneToMany(mappedBy="veterinarioCalificado", cascade=CascadeType.PERSIST, orphanRemoval=true)
	private Collection<CalificacionEntity> calificaciones;

	
	
}
