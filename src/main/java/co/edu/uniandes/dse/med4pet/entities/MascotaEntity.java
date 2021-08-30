package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MascotaEntity extends BaseEntity {

	private String nombre;
	private String raza;
	private String ubicacion;
	private Integer edad;
}
