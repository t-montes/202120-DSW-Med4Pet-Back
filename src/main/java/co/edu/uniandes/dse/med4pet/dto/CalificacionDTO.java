package co.edu.uniandes.dse.med4pet.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CalificacionDTO 
{
	private Long id;
	private String creador;
	private Date fecha;
	private Double puntaje;
	private String descripcion;
}
