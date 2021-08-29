package co.edu.uniandes.dse.med4pet.entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TarjetaCreditoEntity extends BaseEntity
{
	private Integer numeroTarjeta;
	private Integer codigoSeguridad;
	private String fechaVencimiento;
}
