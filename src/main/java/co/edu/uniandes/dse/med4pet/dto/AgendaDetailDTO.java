package co.edu.uniandes.dse.med4pet.dto;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaDetailDTO extends AgendaDTO{
	
	//===========================================================================
	// Asociaciones
	//===========================================================================
	
	/**
	 * Representa la lista de citas que ha tomado  veterinario; esta es la "agenda" como tal.
	 */
	private List<CitaDTO> citas = new ArrayList<>();
	
}
