package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class RegistroMedicoEntity extends BaseEntity 
{
	private String identificacion;
	private String fechaExpedicion;
	private String imagen;
}
