package co.edu.uniandes.dse.med4pet.dto;

import java.time.LocalDateTime;

import co.edu.uniandes.dse.med4pet.entities.EstadoCita;
import lombok.Data;

@Data
public class CitaDTO {
	
	//===========================================================================
	// Atributos
	//===========================================================================

	/**
	 * Identificador
	 */
	private Long id;
	
	/**
	 * Representa la fecha y la hora en la que se ha realizado/realizará la cita.
	 */
	private LocalDateTime fecha;
	
	/**
	 * Representa el estado de la cita; este puede ser CANCELADA, REALIZADA, PENDIENTE.
	 */
	private EstadoCita estado;
	
	/**
	 * Representa el costo que tuvo/tendrá la cita.
	 */
	private Double costo;

	/**
	 * Es false si algún cliente ya agendó la cita; es true de lo contrario.
	 */
	private Boolean disponible;
	
	/**
	 * Representa la duración (en minutos) de la cita.	
	 */
	private Integer duracion;
		
	//===========================================================================
	// Asociaciones
	//===========================================================================

	/**
	 * Representa la mascota paciente que fue atendida durante la cita.
	 */
	private MascotaDTO paciente;
	
	/**
	 * Representa el medio de pago con el que el cliente pagó/pagará esta cita.
	 */
	private MedioDePagoDTO medioDePago;
}
