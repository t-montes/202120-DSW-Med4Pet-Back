package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PSEEntity extends MedioDePagoEntity
{
	//===========================================================================
	// Atributos
	//===========================================================================

	/**
	 * Representa el banco al que está destinado el método de pago.
	 */
	private String banco;
	
	/**
	 * Representa el número de la tarjeta con el cual identificar la cuenta en el banco.
	 */
	private String numeroTarjeta;
	
}
