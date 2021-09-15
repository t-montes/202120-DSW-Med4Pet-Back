package co.edu.uniandes.dse.med4pet.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PSEDTO extends MedioDePagoDTO{
	
	private String banco;
	private String numeroTarjeta;

}
