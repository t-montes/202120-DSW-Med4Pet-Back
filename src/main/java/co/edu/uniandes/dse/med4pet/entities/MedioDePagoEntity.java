package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity

public abstract class MedioDePagoEntity extends BaseEntity{

	//===========================================================================
	// Atributos
	//===========================================================================

	/**
	 * Representa el nombre que le brinda el cliente o la plataforma al medio de pago
	 * para distinguirlo fácilmente de los demás.
	 */
	private String nombre;
	
	//===========================================================================
	// Asociaciones
	//===========================================================================
	
	/**
	 * Representa el cliente asociado que es el dueño de este medio de pago.
	 */
	@OneToOne
	private ClienteEntity clienteAsociado;
	
	/**
	 * Representa la cita que se pagó con este medio de pago en específico.
	 */
	@OneToOne
	private CitaEntity citaAsociada;
	
}
