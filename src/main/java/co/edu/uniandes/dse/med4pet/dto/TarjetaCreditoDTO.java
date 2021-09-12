package co.edu.uniandes.dse.med4pet.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TarjetaCreditoDTO {
	
	private Long numeroTarjeta;
	private Integer codigoSeguridad;
	private Date fechaVencimiento;
}
