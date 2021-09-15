package co.edu.uniandes.dse.med4pet.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TarjetaCreditoDTO extends MedioDePagoDTO {
	
	private String numeroTarjeta;
	private String codigoSeguridad;
	private Date fechaVencimiento;
}
