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

import co.edu.uniandes.dse.med4pet.entities.AgendaEntity;
import co.edu.uniandes.dse.med4pet.entities.CitaEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.services.AgendaService;
import co.edu.uniandes.dse.med4pet.dto.AgendaDetailDTO;
import co.edu.uniandes.dse.med4pet.dto.CitaDTO;

@RestController
@RequestMapping("/agendas")
public class AgendaController {
	
	@Autowired
	private AgendaService agendaService;
   
	@Autowired
	private ModelMapper modelMapper;
   
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<AgendaDetailDTO> findAll(){
		List<AgendaEntity> agendas = agendaService.getAgendas();
		return modelMapper.map(agendas, new TypeToken<List<AgendaDetailDTO>>() {}.getType());
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public AgendaDetailDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException {
		AgendaEntity agendaEntity = agendaService.getAgenda(id);
		return modelMapper.map(agendaEntity, AgendaDetailDTO.class);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public AgendaDetailDTO create(@RequestBody AgendaDetailDTO agendaDTO) throws IllegalOperationException {
		AgendaEntity agendaEntity = agendaService.createAgenda(modelMapper.map(agendaDTO, AgendaEntity.class));
		return modelMapper.map(agendaEntity, AgendaDetailDTO.class);
	}

	@PostMapping(value = "/{agendaId}/citas/{citaId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CitaDTO addCita(@PathVariable("agendaId") Long agendaId, 
			@PathVariable("citaId") Long citaId) throws EntityNotFoundException {
		CitaEntity citaEntity = agendaService.addCita(agendaId, citaId);
		return modelMapper.map(citaEntity, CitaDTO.class);
	}
}
