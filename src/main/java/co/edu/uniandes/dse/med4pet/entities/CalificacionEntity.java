package co.edu.uniandes.dse.med4pet.entities;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class CalificacionEntity extends BaseEntity
{
	private String creador;
	private Double puntaje;
	private Date fecha;
}
