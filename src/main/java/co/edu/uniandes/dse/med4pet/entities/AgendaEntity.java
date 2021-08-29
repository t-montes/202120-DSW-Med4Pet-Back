package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
public class AgendaEntity extends BaseEntity{

	private Integer numeroCitasPendientes;
	private Integer numeroCitasRealizadas;
	private Integer numeroCitasCanceladas;
	
}
