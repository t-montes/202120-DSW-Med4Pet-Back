package co.edu.uniandes.dse.med4pet.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;
import lombok.Getter;

import java.util.Date;

@Getter
@Setter
@Entity
public class CitaEntity extends BaseEntity{

	//===========================================================================
	// Atributos
	//===========================================================================

	/**
	 * Representa la fecha en la que se ha realizado/realizará la cita.
	 */
	private Date fecha;
	
	/**
	 * Representa el estado de la cita; este puede ser CANCELADA, REALIZADA, PENDIENTE.
	 */
	private EstadoCita estado;
	
	/**
	 * Representa el costo que tuvo/tendrá la cita.
	 */
	private Double costo;

	//===========================================================================
	// Asociaciones
	//===========================================================================

	/**
	 * Representa la lista de servicios que se brindaron en la cita.
	 */
	@PodamExclude
	@ManyToMany
	private Collection<ServicioEntity> serviciosTomados;
	
	/**
	 * Representa la agenda a la que está vinculada esta cita.
	 */
	@PodamExclude
	@ManyToOne
	private AgendaEntity agenda;
	
	/**
	 * Representa la mascota pacinte que fue atendida durante la cita.
	 */
	@PodamExclude
	@ManyToOne
	private MascotaEntity paciente;
	
	/**
	 * Representa el medio de pago con el que el cliente pagó/pagará esta cita.
	 */
	@PodamExclude
	@OneToOne(mappedBy="citaAsociada", cascade = CascadeType.PERSIST)
	private MedioDePagoEntity medioDePago;
	
}
