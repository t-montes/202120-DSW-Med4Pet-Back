package co.edu.uniandes.dse.med4pet.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;
import lombok.Getter;

@Getter
@Setter
@Entity
public class AgendaEntity extends BaseEntity{

	//===========================================================================
	// Atributos
	//===========================================================================
	
	/**
	 * Representa el número de todas las citas de la agenda que el cliente @cliente
	 * tiene a futuro.
	 */
	private Integer numeroCitasPendientes;
	
	/**
	 * Representa el número de todas las citas de la agenda que el cliente @cliente
	 * ha tomado anteriormente.
	 */
	private Integer numeroCitasRealizadas;
	
	/**
	 * Representa el número de todas las citas de la agenda del cliente @cliente que
	 * han sido canceladas anteriormente y a futuro.
	 */
	private Integer numeroCitasCanceladas;
	
	//===========================================================================
	// Asociaciones
	//===========================================================================
	
	/**
	 * Representa el cliente que posee la agenda.
	 */
	@PodamExclude
	@OneToOne
	private ClienteEntity cliente;
	
	/**
	 * Representa la lista de citas que ha tomado el cliente; esta es la "agenda" como tal.
	 */
	@PodamExclude
	@OneToMany(mappedBy="agenda")
	private Collection<CitaEntity> citas;
	
}
