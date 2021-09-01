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

	private String nombre;
	private String raza;
	private String ubicacion;
	private Date fechaNacimiento;
	
	
	@PodamExclude
	@ManyToOne
	private Collection<ClienteEntity> duenio;
	
	@PodamExclude
	@OneToMany(mappedBy="paciente")
	private Collection<CitaEntity> citasAtendidas;
}
