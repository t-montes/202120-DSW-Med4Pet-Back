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

import co.edu.uniandes.dse.med4pet.dto.PSEDTO;
import co.edu.uniandes.dse.med4pet.entities.PSEEntity;
import co.edu.uniandes.dse.med4pet.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.med4pet.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.med4pet.services.PSEService;

@RestController
@RequestMapping("/pses")
public class PSEController {
	
	// Para crear los datos primero se deben crear CitaController y ClienteController
	
	@Autowired
	private PSEService pseService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<PSEDTO> findAll()
	{
		List<PSEEntity> pseAccounts = pseService.getPSEs();
		return modelMapper.map(pseAccounts, new TypeToken<List<PSEDTO>>(){}.getType());
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public PSEDTO findOne(@PathVariable("id") Long id) throws EntityNotFoundException
	{
		PSEEntity pseEntity = pseService.getPSE(id);
		return modelMapper.map(pseEntity, PSEDTO.class);
	}
	
    @PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PSEDTO create(@RequestBody PSEDTO pseDTO) throws IllegalOperationException
    {
    	PSEEntity pseEntity = pseService.createPSE(modelMapper.map(pseDTO, PSEEntity.class));
		return modelMapper.map(pseEntity, PSEDTO.class);
	}
}
