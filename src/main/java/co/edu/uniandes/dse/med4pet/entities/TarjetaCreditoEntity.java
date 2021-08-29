package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class TarjetaCreditoEntity extends MedioDePagoEntity
{
	private Integer numeroTarjeta;
	private Integer codigoSeguridad;
	private Date fechaVencimiento;
}
