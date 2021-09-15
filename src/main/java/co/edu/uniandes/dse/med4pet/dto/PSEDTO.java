package co.edu.uniandes.dse.med4pet.dto;

import lombok.Data;

@Data
public class PSEDTO extends MedioDePagoDTO{
	
	private String banco;
	private String numeroTarjeta;

}
