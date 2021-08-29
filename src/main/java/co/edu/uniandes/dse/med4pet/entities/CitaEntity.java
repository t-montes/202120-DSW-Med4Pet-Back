package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;
import lombok.Setter;
import lombok.Getter;

import java.util.Date;

@Getter
@Setter
@Entity
public class CitaEntity extends BaseEntity{
	
	private Date fecha;
	private EstadoCita estado;
	private Double costo;
	
}
