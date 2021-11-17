package co.edu.uniandes.dse.med4pet.dto;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDetailDTO extends ClienteDTO {

	private List<MascotaDTO> mascotas = new ArrayList<>();
	private List<CalificacionDTO> calificaciones = new ArrayList<>();
	

}
