package co.edu.uniandes.dse.med4pet.dto;

import java.util.Date;

import lombok.Data;

@Data

public class RegistroMedicoDTO 
{
	private Long id;
	private String identificacion;
	private Date fechaExpedicion;
	private String imagen;
}
