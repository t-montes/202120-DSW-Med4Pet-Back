package co.edu.uniandes.dse.med4pet.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class ClienteEntity extends BaseEntity {

	//===========================================================================
	// Atributos
	//===========================================================================
	
	/**
	 * Representa el nombre del cliente.
	 */
	private String nombre;
	
	/**
	 * Representa la calificacion dada al cliente.
	 */
	private Double calificacion;
	
	//===========================================================================
	// Asociaciones
	//===========================================================================
		
	/**
	 * Representa el contacto para comunicarse con el cliente.
	 */	

	@PodamExclude
	@OneToOne(mappedBy ="cliente")
	private ContactoEntity contacto;
	
	/**
	 * Representa la calificacion que se le da al cliente.
	 */
	@PodamExclude
	@OneToMany(mappedBy="clienteCalificado", cascade=CascadeType.PERSIST, orphanRemoval=true)
	private Collection<CalificacionEntity> calificaciones;
	
	/**
	 * Representa el medio de pago que usara el cliente para cancelar la consulta.
	 */
	@PodamExclude
	@OneToOne(mappedBy="clienteAsociado")
	private MedioDePagoEntity medioDePagoPredeterminado;
	
	/**
	 * Representa la mascota que tiene el cliente.
	 */
	@PodamExclude
	@OneToMany(mappedBy="duenio", cascade=CascadeType.PERSIST)
	private Collection<MascotaEntity> mascotas;
}