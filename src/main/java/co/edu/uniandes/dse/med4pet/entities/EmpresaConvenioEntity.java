package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmpresaConvenioEntity extends BaseEntity {

	private String nombre;
	private String nit;
	private String servicio;
}
