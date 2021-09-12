package co.edu.uniandes.dse.med4pet.dto;

import java.util.Date;

import lombok.Data;


@Data
public class TarjetaCreditoDTO {
	
	private Long numeroTarjeta;
	private Integer codigoSeguridad;
	private Date fechaVencimiento;
}
