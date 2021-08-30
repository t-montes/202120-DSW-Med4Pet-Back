package co.edu.uniandes.dse.med4pet.entities;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class RegistroMedicoEntity extends BaseEntity 
{
	private String identificacion;
	private Date fechaExpedicion;
	private String imagen;
}
