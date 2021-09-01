package co.edu.uniandes.dse.med4pet.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;


import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
@Getter
@Setter
public class ServicioEntity extends BaseEntity{

	private String nombre;
	
	
	@PodamExclude
	@ManyToMany
	private Collection<VeterinarioEntity> prestadores;
	
	
	@PodamExclude
	@ManyToMany(mappedBy="serviciosTomados")
	private Collection<CitaEntity> citas;
	
}
