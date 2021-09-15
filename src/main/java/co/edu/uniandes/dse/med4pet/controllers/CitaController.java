package co.edu.uniandes.dse.med4pet.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.med4pet.entities.CitaEntity;
import co.edu.uniandes.dse.med4pet.services.CitaService;
import co.edu.uniandes.dse.med4pet.dto.CitaDTO;

@RestController
@RequestMapping("/citas")
public class CitaController {

	@Autowired
	private CitaService citaService;
		
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<CitaDTO> findAll(){
		List<CitaEntity> citas = citaService.getCitas();
		return modelMapper.map(citas, new TypeToken<List<CitaDTO>>() {}.getType());
	}
	
}
