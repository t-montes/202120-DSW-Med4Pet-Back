package co.edu.uniandes.dse.med4pet.entities;

import java.util.Collection;

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

	private String nombre;
	private Double calificacion;
	
	
	@PodamExclude
	@OneToOne(mappedBy="cliente")
	private Collection<ContactoEntity> contacto;
	
	
	@PodamExclude
	@OneToMany(mappedBy="clienteCalificado")
	private Collection<CalificacionEntity> calificaciones;
	
	
	@PodamExclude
	@OneToOne(mappedBy="cliente")
	private Collection<AgendaEntity> agenda;
	
	
	@PodamExclude
	@OneToOne(mappedBy="clienteAsociado")
	private Collection<MedioDePagoEntity> medioDePagoPredeterminado;
	
	@PodamExclude
	@OneToMany(mappedBy="duenio")
	private Collection<MascotaEntity> mascotas;
}