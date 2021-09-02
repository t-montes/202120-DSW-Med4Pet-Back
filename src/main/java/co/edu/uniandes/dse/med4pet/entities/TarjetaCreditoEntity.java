package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class TarjetaCreditoEntity extends MedioDePagoEntity
{
	//===========================================================================
	// Atributos
	//===========================================================================

	/**
	 * Representa el número de la tarjeta con el cual se asocia el método de pago.
	 */
	private String numeroTarjeta;
	
	/**
	 * Representa el código de seguridad de la tarjeta; permite agregar un nivel más de seguridad al pago
	 * con tarjeta de crédito.
	 */
	private String codigoSeguridad;
	
	/**
	 * Representa la fecha en la que vence la validez de la trajeta de crédito; permite determinar si la
	 * tarjeta se encuentra activa.
	 */
	private Date fechaVencimiento;
}
