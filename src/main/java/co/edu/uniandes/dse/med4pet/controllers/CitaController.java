package co.edu.uniandes.dse.med4pet.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniandes.dse.med4pet.entities.CitaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.services.CitaService;
import co.edu.uniandes.dse.med4pet.dto.CitaDetailDTO;

@RestController
@RequestMapping("/citas")
public class CitaController {

	@Autowired
	private CitaService citaService;
		
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<CitaDetailDTO> findAll(){
		List<CitaEntity> citas = citaService.getCitas();
		return modelMapper.map(citas, new TypeToken<List<CitaDetailDTO>>() {}.getType());
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public CitaDetailDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
		CitaEntity citaEntity = citaService.getCita(id);
		return modelMapper.map(citaEntity, CitaDetailDTO.class);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CitaDetailDTO create(@RequestBody CitaDetailDTO citaDTO) throws IllegalOperationException{
		CitaEntity citaEntity = citaService.createCita(modelMapper.map(citaDTO, CitaEntity.class));
		return modelMapper.map(citaEntity, CitaDetailDTO.class);
	}
}
