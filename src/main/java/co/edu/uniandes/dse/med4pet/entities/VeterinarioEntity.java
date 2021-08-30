package co.edu.uniandes.dse.med4pet.entities;

import java.util.List;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class VeterinarioEntity extends BaseEntity{
	
	private String nombre;
	private String especialidad;
	private	String[] experienciaPrevia;
	private String certificadoEntrenamiento;
	private Double calificacion;
	
	
}
