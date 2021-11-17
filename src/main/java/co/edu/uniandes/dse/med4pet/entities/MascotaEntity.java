package co.edu.uniandes.dse.med4pet.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class MascotaEntity extends BaseEntity {

	//===========================================================================
	// Atributos
	//===========================================================================
	
	/**
	 * Representa el nombre de la mascota.
	 */
	private String nombre;
	
	/**
	 * Representa si la mascota es un gato, perro, hamster, ...
	 */
	private TipoMascota tipoMascota;
	
	/**
	 * Representa el raza de la mascota.
	 */
	private String raza;
	
	/**
	 * Representa la ubicacion de la mascota.
	 */
	private String ubicacion;
	
	/**
	 * Representa la fecha nacimiento de la mascota.
	 */
	private Date fechaNacimiento;
	
	
	//===========================================================================
	// Asociaciones
	//===========================================================================
	
	/**
	 * Representa al cliente duenio de la mascota.
	 */	
	@PodamExclude
	@ManyToOne
	private ClienteEntity duenio;
	
	
	/**
	 * Representa las citas atendidas a la mascota paciente.
	 */		
	@PodamExclude
	@OneToMany(mappedBy="paciente")
	private Collection<CitaEntity> citasAtendidas;
}
