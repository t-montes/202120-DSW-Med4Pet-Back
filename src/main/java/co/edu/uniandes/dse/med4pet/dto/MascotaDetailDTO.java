package co.edu.uniandes.dse.med4pet.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MascotaDetailDTO extends MascotaDTO {
	
	private List<CitaDetailDTO> citasAtendidas= new ArrayList<>();

}
