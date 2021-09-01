package co.edu.uniandes.dse.med4pet.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class EmpresaConvenioEntity extends BaseEntity {

	private String nombre;
	private String nit;
	private String servicio;
	
	
	@PodamExclude
	@OneToOne(mappedBy="empresa")
	private Collection<ContactoEntity> contacto;
}
