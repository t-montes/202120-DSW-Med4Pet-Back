package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class EmpresaConvenioEntity extends BaseEntity {
	
	//===========================================================================
	// Atributos
	//===========================================================================

	/**
	 * Representa el nombre de la empresa asociada.
	 */
	private String nombre;
	/**
	 * Representa el nit propio de la empresa asociada.
	 */
	private String nit;
	
	/**
	 * Representa el servicio que presta de la empresa asociada.
	 */
	private String servicio;

	//===========================================================================
	// Asociaciones
	//===========================================================================

	/**
	 * Representa con contacto con el que se puede comunicar con la empresa.
	 */	
	@PodamExclude
	@OneToOne
	private ContactoEntity contacto;
}
