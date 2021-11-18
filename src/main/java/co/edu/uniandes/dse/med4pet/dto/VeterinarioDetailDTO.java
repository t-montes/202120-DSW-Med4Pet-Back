package co.edu.uniandes.dse.med4pet.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeterinarioDetailDTO extends VeterinarioDTO {
	
	/**
	 * Representa las calificaciones del veterinario.
	 */
	private List<CalificacionDTO> calificaciones;
}
