package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClienteEntity extends BaseEntity {

	private String nombre;
	private Double calificacion;
}