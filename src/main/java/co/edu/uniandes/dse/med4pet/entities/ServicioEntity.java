package co.edu.uniandes.dse.med4pet.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;


import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class ServicioEntity extends BaseEntity{

	//===========================================================================
	// Atributos
	//===========================================================================
	
	/**
	 * Representa el nombre del servicio que se ofrecera.
	 */
	private String nombre;
	
	
	//===========================================================================
	// Asociaciones
	//===========================================================================

	/**
	 * Representa el veteriario asignado para servicio.
	 */
	@PodamExclude
	@ManyToMany
	private Collection<VeterinarioEntity> prestadores;
	
	/**
	 * Representa la cita fue dada al cliente para el servicio.
	 */
	@PodamExclude
	@ManyToMany(mappedBy="serviciosTomados")
	private Collection<CitaEntity> citas;
	
}
