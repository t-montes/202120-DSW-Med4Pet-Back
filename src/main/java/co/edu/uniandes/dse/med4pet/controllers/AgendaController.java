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

import co.edu.uniandes.dse.med4pet.entities.AgendaEntity;
import co.edu.uniandes.dse.med4pet.services.AgendaService;
import co.edu.uniandes.dse.med4pet.dto.AgendaDTO;

@RestController
@RequestMapping("/agendas")
public class AgendaController {
	
	@Autowired
	private AgendaService agendaService;
   
	@Autowired
	private ModelMapper modelMapper;
   
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<AgendaDTO> findAll(){
		List<AgendaEntity> agendas = agendaService.getAgendas();
		return modelMapper.map(agendas, new TypeToken<List<AgendaDTO>>() {}.getType());
	}
}
