package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class ContactoEntity extends BaseEntity{
	//===========================================================================
	// Atributos
	//===========================================================================

	/**
	 * Representa el telefono del contacto
	 */
	private String telefono;
	/**
	 * Representa el correo del contacto
	 */
	private String correo;
	//===========================================================================
	// Asociaciones
	//===========================================================================

	/**
	 * Representa el cliente asociado al contacto.
	 */
	@PodamExclude
	@OneToOne(mappedBy ="contacto")
	private ClienteEntity cliente;
	/**
	 * Representa el veterinario asociado al contacto
	 */
	@PodamExclude
	@OneToOne(mappedBy ="contacto")
	private VeterinarioEntity veterinario;
	/**
	 * Representa la empresa asociada al contacto
	 */
	@PodamExclude
	@OneToOne(mappedBy ="contacto")
	private EmpresaConvenioEntity empresaConvenio;
	
}
