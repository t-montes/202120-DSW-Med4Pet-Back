package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity

public abstract class MedioDePagoEntity extends BaseEntity{

	private String nombre;
	
}
